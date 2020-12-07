package com.github.berry120.adventofcode_2020;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day7 {

  private Map<String, List<SimpleEntry<Integer, String>>> bagMap;

  public Day7(String input) {
    bagMap = input.lines()
        .collect(Collectors.toMap(
            l -> l.substring(0, l.indexOf("bags")).trim(),
            l -> Arrays.stream(l.substring(l.indexOf("contain") + 7, l.length() - 1).split(","))
                .map(s -> s.trim().split(" "))
                .filter(arr -> arr.length == 4)
                .map(arr -> new SimpleEntry<>(Integer.parseInt(arr[0]), arr[1] + " " + arr[2]))
                .collect(Collectors.toList()))
        );
  }

  private boolean hasGold(String col) {
    return bagMap.getOrDefault(col, List.of()).stream()
        .anyMatch(bag -> bag.getValue().equals("shiny gold") || hasGold(bag.getValue()));
  }

  private int totalBags(String col) {
    return bagMap.getOrDefault(col, List.of())
               .stream()
               .mapToInt(bagDesc -> totalBags(bagDesc.getValue()) * bagDesc.getKey())
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
