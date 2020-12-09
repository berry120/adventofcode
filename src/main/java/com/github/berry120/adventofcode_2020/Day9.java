package com.github.berry120.adventofcode_2020;

import java.util.Arrays;

public class Day9 {

  private final long[] nums;

  public Day9(String input) {
    nums = Arrays.stream(input.split("\n")).mapToLong(Long::parseLong).toArray();
  }

  private boolean isValid(long[] arr, long sumTo) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length; j++) {
        if (i != j && arr[i] + arr[j] == sumTo) {
          return true;
        }
      }
    }
    return false;
  }

  public long part1() {
    for (int i = 25; i < nums.length; i++) {
      if (!isValid(Arrays.stream(Arrays.copyOfRange(nums, i - 25, i)).toArray(), nums[i])) {
        return nums[i];
      }
    }
    throw new AssertionError();
  }

  public long part2() {
    long sumTo = part1();
    for (int base = 0; base < nums.length; base++) {
      for (int i = base + 1; i < nums.length; i++) {
        if (Arrays.stream(Arrays.copyOfRange(nums, base, i + 1)).sum() == sumTo) {
          return Arrays.stream(Arrays.copyOfRange(nums, base, i + 1)).min().orElseThrow() + Arrays
              .stream(Arrays.copyOfRange(nums, base, i + 1)).max().orElseThrow();
        }
      }
    }
    throw new AssertionError();
  }

}
