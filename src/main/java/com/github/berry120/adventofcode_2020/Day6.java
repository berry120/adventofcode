package com.github.berry120.adventofcode_2020;

import java.util.Arrays;

public class Day6 {

  private final String[] groups;

  public Day6(String input) {
    groups = input.split("\n\n");
  }

  public long part1() {
    return Arrays.stream(groups)
        .mapToLong(group -> group.chars().distinct().filter(c -> c != '\n').count())
        .sum();
  }

  public long part2() {
    return Arrays.stream(groups)
        .mapToLong(group -> group.chars().distinct()
            .filter(c -> group.lines().allMatch(line -> line.indexOf(c) >= 0))
            .count())
        .sum();
  }

}
