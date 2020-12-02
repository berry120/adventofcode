package com.github.berry120.adventofcode_2020;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day1 {

  private final String input;

  private Set<Integer> getNumSet() {
    return Arrays.stream(input.split("\n")).map(Integer::parseInt)
        .collect(Collectors.toSet());
  }

  public int part1() {
    Set<Integer> nums = getNumSet();

    for (int num : nums) {
      int expected = 2020 - num;
      if (nums.contains(expected)) {
        return num * expected;
      }
    }
    return 0;
  }

  public int part2() {
    Set<Integer> nums = getNumSet();

    for (int num : nums) {
      int intermediate = 2020 - num;
      for (int num2 : nums) {
        int expected2 = intermediate - num2;
        if (nums.contains(expected2)) {
          return num * num2 * expected2;
        }
      }
    }
    return 0;
  }

}
