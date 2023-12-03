package com.github.berry120.adventofcode_2023;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day1 {

  private String input;

  public int part1() {
    return sumEdges(input);
  }

  public int part2() {
    return sumEdges(
        input
            .replace("one", "o1e")
            .replace("two", "t2")
            .replace("three", "t3e")
            .replace("four", "4")
            .replace("five", "5e")
            .replace("six", "6")
            .replace("seven", "7n")
            .replace("eight", "e8t")
            .replace("nine", "n9e"));
  }

  private int sumEdges(String input) {
    return input
        .replaceAll("[^\\d\\n]", "")
        .lines()
        .mapToInt(s -> Integer.parseInt(s.charAt(0) + s.substring(s.length() - 1)))
        .sum();
  }
}
