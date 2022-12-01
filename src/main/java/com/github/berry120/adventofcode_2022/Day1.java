package com.github.berry120.adventofcode_2022;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public class Day1 {

  private final String input;

  public int part1() {
    return Arrays.stream(input.split("\n\n"))
            .mapToInt(s -> s.lines().mapToInt(Integer::parseInt).sum())
            .max()
            .orElse(0);
  }

  public int part2() {
    return Arrays.stream(input.split("\n\n"))
            .mapToInt(s -> s.lines().mapToInt(Integer::parseInt).sum())
            .sorted()
            .skip(input.split("\n\n").length-3)
            .sum();
  }
}
