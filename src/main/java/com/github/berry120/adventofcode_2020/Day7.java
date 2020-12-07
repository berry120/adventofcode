package com.github.berry120.adventofcode_2020;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day7 {

  private static final Pattern PATTERN = Pattern.compile("(\\d+) (.+?) bag");
  private final Map<String, Map<String, Integer>> bagMap;

  public Day7(String input) {
    bagMap = input.lines().collect(Collectors.toMap(
        line -> line.substring(0, line.indexOf("bags contain") - 1),
        line -> PATTERN.matcher(line).results().collect(Collectors.toMap(
            match -> match.group(2),
            match -> Integer.parseInt(match.group(1))))
    ));
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
