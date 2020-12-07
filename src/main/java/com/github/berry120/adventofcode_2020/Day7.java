package com.github.berry120.adventofcode_2020;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Day7 {

  private final Map<String, Map<String, Integer>> bagMap;

  public Day7(String input) {
    bagMap = input.lines().collect(Collectors.toMap(
        l -> l.substring(0, l.indexOf("bags") - 1),
        l -> Arrays.stream(l.substring(l.indexOf("contain") + 8, l.length() - 1).split(", "))
            .filter(s -> !s.equals("no other bags"))
            .map(s -> s.split(" "))
            .collect(
                Collectors.toMap(arr -> arr[1] + " " + arr[2], arr -> Integer.parseInt(arr[0])))));
  }

  private boolean hasGold(String col) {
    return bagMap.getOrDefault(col, Map.of()).entrySet().stream()
        .anyMatch(bag -> bag.getKey().equals("shiny gold") || hasGold(bag.getKey()));
  }

  private int totalBags(String col) {
    return bagMap.getOrDefault(col, Map.of()).entrySet().stream()
               .mapToInt(bagDesc -> totalBags(bagDesc.getKey()) * bagDesc.getValue())
               .sum() + 1;
  }

  public long part1() {
    return bagMap.keySet().stream()
        .filter(this::hasGold)
        .count();
  }

  public long part2() {
    return totalBags("shiny gold") - 1;
  }

}
