package com.github.berry120.adventofcode_2022;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day2 {

  private final String input;

  public int part1() {
    return input
        .lines()
        .mapToInt(
            str ->
                str.charAt(str.length() - 1)
                    - 87
                    + ((str.equals("A X") || str.equals("B Y") || str.equals("C Z"))
                        ? 3
                        : (str.equals("A Y") || str.equals("B Z") || str.equals("C X")) ? 6 : 0))
        .sum();
  }

  public int part2() {
    return input
        .lines()
        .mapToInt(
            str ->
                3 * (str.charAt(str.length() - 1) - 88)
                    + ((str.equals("A X") || str.equals("B Z") || str.equals("C Y"))
                        ? 3
                        : (str.equals("A Y") || str.equals("B X") || str.equals("C Z"))
                            ? 1
                            : (str.equals("A Z") || str.equals("B Y") || str.equals("C X"))
                                ? 2
                                : 0))
        .sum();
  }
}
