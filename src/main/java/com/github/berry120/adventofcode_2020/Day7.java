package com.github.berry120.adventofcode_2020;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7 {

  private Map<String, List<SimpleEntry<Integer, String>>> bagMap = new HashMap<>();

  public Day7(String input) {
    input.lines().forEach(line -> {
      String rootBag = line.substring(0, line.indexOf("bags")).trim();

      for (String containingBag : line.substring(line.indexOf("contain") + 7, line.length() - 1)
          .trim().split(",")) {
        Matcher m = Pattern.compile("(\\d+) (.+) bags?").matcher(containingBag);
        bagMap.put(rootBag,
            m.find() ? Stream.concat(bagMap.getOrDefault(rootBag, List.of()).stream(),
                Stream.of(new SimpleEntry<>(Integer.parseInt(m.group(1)), m.group(2))))
                .collect(Collectors.toList()) : List.of());
      }
    });
  }

  private int fullBagSize(String col) {
    return bagMap.getOrDefault(col, new ArrayList<>())
               .stream()
               .mapToInt(bagDesc -> fullBagSize(bagDesc.getValue()) * bagDesc.getKey())
               .sum() + 1;
  }

  private boolean containsGold(String col) {
    return bagMap.getOrDefault(col, new ArrayList<>()).stream()
        .anyMatch(
            bagDesc -> bagDesc.getValue().equals("shiny gold") || containsGold(bagDesc.getValue()));
  }

  public long part1() {
    return bagMap.keySet().stream()
        .filter(this::containsGold)
        .count();
  }

  public long part2() {
    return fullBagSize("shiny gold") - 1;
  }

}
