package com.github.berry120.adventofcode_2020;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Day6 {

  private final String[] groups;

  public Day6(String input) {
    groups = input.split("\n\n");
  }

  private Set<Character> unique(String input) {
    return input.chars()
        .mapToObj(i -> (char) i)
        .filter(c -> c != '\n')
        .collect(Collectors.toSet());
  }

  public int part1() {
    return Arrays.stream(groups)
        .mapToInt(group -> unique(group).size())
        .sum();
  }

  public long part2() {
    return Arrays.stream(groups)
        .mapToLong(group -> unique(group).stream()
            .filter(c -> group.lines().allMatch(line -> line.indexOf(c) >= 0))
            .count())
        .sum();
  }

}
