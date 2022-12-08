package com.github.berry120.adventofcode_2022;

import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day6 {

  private final String input;

  public int part1() {
    return posCheck(4);
  }

  public int part2() {
    return posCheck(14);
  }

  private int posCheck(int uniques) {
    for (int i = 0; i < input.length(); i++) {
      if (input.substring(i, i + uniques).chars().boxed().collect(Collectors.toSet()).size()
          == uniques) {
        return i + uniques;
      }
    }
    return 0;
  }
}
