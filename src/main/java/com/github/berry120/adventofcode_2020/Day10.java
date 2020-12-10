package com.github.berry120.adventofcode_2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Day10 {

  private final int[] nums;
  private final int top;
  private final List<Integer> pivots = new ArrayList<>();

  public Day10(String input) {
    nums = input.lines().mapToInt(Integer::parseInt).toArray();
    Arrays.sort(nums);
    top = nums[nums.length - 1] + 3;

    for (int i = 1; i < nums.length - 1; i++) {
      if (nums[i + 1] - nums[i] == 3 && nums[i] - nums[i - 1] == 3) {
        pivots.add(i);
      }
    }
  }

  Set<List<Integer>> sequences = new HashSet<>();

  public long arrangements(List<Integer> nums, int base, int top) {
    int ret = 0;
    for (int i = 0; i < nums.size(); i++) {
      List<Integer> copy = new ArrayList<>(nums);
      copy.remove(i);
      if (!sequences.contains(copy) && isValid(copy, base, top)) {
        sequences.add(copy);
        ret += arrangements(copy, base, top);
      }
    }
    return ret;
  }

  private boolean isValid(List<Integer> nums, int base, int top) {
    if (nums.get(0) > base + 3) {
      return false;
    }
    if (nums.get(nums.size() - 1) < top - 3) {
      return false;
    }
    for (int i = 1; i < nums.size(); i++) {
      if (nums.get(i) - nums.get(i - 1) > 3) {
        return false;
      }
    }
    return true;
  }

  public long part2() {
    List<Integer> numList = Arrays.stream(nums).boxed().collect(Collectors.toList());

    long ret = 1;

    for (int i = 0; i < pivots.size() + 1; i++) {
      int startIdx = i == 0 ? 0 : (pivots.get(i - 1)) + 1;
      int endIdx = i == pivots.size() ? numList.size() : pivots.get(i);
      int base;
      if (startIdx == 0) {
        base = 0;
      } else {
        base = numList.get(startIdx - 1);
      }
      int top;
      if (endIdx == numList.size()) {
        top = this.top;
      } else {
        top = numList.get(endIdx);
      }

      List<Integer> subList = numList.subList(startIdx, endIdx);
      sequences = new HashSet<>();
      arrangements(subList, base, top);
      ret *= (sequences.size() + 1);
    }

    return ret;
  }

  public long part1() {
    int diff1 = 1;
    int diff3 = 1;

    for (int i = 0; i < nums.length - 1; i++) {
      if (Math.abs(nums[i] - nums[i + 1]) == 1) {
        diff1++;
      }
      if (Math.abs(nums[i] - nums[i + 1]) == 3) {
        diff3++;
      }
    }
    return diff3 * diff1;
  }

}
