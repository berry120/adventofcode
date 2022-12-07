package com.github.berry120.adventofcode_2022;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day4 {

  private final String input;

  public int part1() {
    int count = 0;
    for (String line : input.split("\n")) {
      String parts[] = line.split(",");

      int firstStart = Integer.parseInt(parts[0].split("-")[0]);
      int firstEnd = Integer.parseInt(parts[0].split("-")[1]);
      Set<Integer> firstSet = new HashSet<>();
      for (int i = firstStart; i <= firstEnd; i++) {
        firstSet.add(i);
      }

      int secondStart = Integer.parseInt(parts[1].split("-")[0]);
      int secondEnd = Integer.parseInt(parts[1].split("-")[1]);
      Set<Integer> secondSet = new HashSet<>();
      for (int i = secondStart; i <= secondEnd; i++) {
        secondSet.add(i);
      }

      Set<Integer> largeSet = firstSet.size()>secondSet.size()?firstSet:secondSet;
      Set<Integer> smallSet = firstSet.size()<=secondSet.size()?firstSet:secondSet;

      smallSet.removeAll(largeSet);
      if(smallSet.isEmpty())count++;
    }
    return count;
  }

  public int part2() {
    int count = 0;
    for (String line : input.split("\n")) {
      String parts[] = line.split(",");

      int firstStart = Integer.parseInt(parts[0].split("-")[0]);
      int firstEnd = Integer.parseInt(parts[0].split("-")[1]);
      Set<Integer> firstSet = new HashSet<>();
      for (int i = firstStart; i <= firstEnd; i++) {
        firstSet.add(i);
      }

      int secondStart = Integer.parseInt(parts[1].split("-")[0]);
      int secondEnd = Integer.parseInt(parts[1].split("-")[1]);
      Set<Integer> secondSet = new HashSet<>();
      for (int i = secondStart; i <= secondEnd; i++) {
        secondSet.add(i);
      }

      Set<Integer> largeSet = new HashSet<>(firstSet);
      largeSet.addAll(secondSet);

      if(largeSet.size()<firstSet.size()+secondSet.size()) count++;
    }
    return count;
  }
}
