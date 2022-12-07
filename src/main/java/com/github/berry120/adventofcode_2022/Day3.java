package com.github.berry120.adventofcode_2022;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day3 {

  private final String input;

  public int part1() {
    int sum = 0;
    for (String line : input.split("\n")) {
      String firstPart = line.substring(0, line.length() / 2);
      String secondPart = line.substring(line.length() / 2);

      Set<Integer> firstSet = firstPart.chars().boxed().collect(Collectors.toSet());
      Set<Integer> secondSet = secondPart.chars().boxed().collect(Collectors.toSet());

      firstSet.retainAll(secondSet);
      for (int c : firstSet) {
        if (Character.isLowerCase(c)) {
          sum += c - 96;
        } else {
          sum += c - 38;
        }
      }
    }
    return sum;
  }

  public int part2() {
    int sum = 0;
    String[] lines = input.split("\n");
    for (int i = 0; i < lines.length; i += 3) {
      Set<Integer> set1 = lines[i].chars().boxed().collect(Collectors.toSet());
      Set<Integer> set2 = lines[i + 1].chars().boxed().collect(Collectors.toSet());
      Set<Integer> set3 = lines[i + 2].chars().boxed().collect(Collectors.toSet());

      set1.retainAll(set2);
      set1.retainAll(set3);

      for (int c : set1) {
        if (Character.isLowerCase(c)) {
          sum += c - 96;
        } else {
          sum += c - 38;
        }
      }
    }
    return sum;
  }
}
