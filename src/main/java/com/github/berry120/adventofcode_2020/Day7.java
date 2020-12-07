package com.github.berry120.adventofcode_2020;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7 {

  private Map<String, List<SimpleEntry<Integer, String>>> bagMap = new HashMap<>();

  public Day7(String input) {
    input.lines().forEach(line -> {
      String rootBag = line.substring(0, line.indexOf("bags")).trim();

      Arrays.stream(line.substring(line.indexOf("contain") + 7, line.length() - 1).split(","))
          .map(s -> s.trim().split(" "))
          .filter(arr -> arr.length == 4)
          .map(arr -> Stream.concat(bagMap.getOrDefault(rootBag, List.of()).stream(),
              Stream.of(new SimpleEntry<>(Integer.parseInt(arr[0]), arr[1] + " " + arr[2])))
              .collect(Collectors.toList()))
          .forEach(containingBag -> bagMap.put(rootBag, containingBag));

    });
  }

  private boolean hasGold(String col) {
    return bagMap.getOrDefault(col, List.of()).stream()
        .anyMatch(bag -> bag.getValue().equals("shiny gold") || hasGold(bag.getValue()));
  }

  private int fullBagSize(String col) {
    return bagMap.getOrDefault(col, List.of())
               .stream()
               .mapToInt(bagDesc -> fullBagSize(bagDesc.getValue()) * bagDesc.getKey())
               .sum() + 1;
  }

  public long part1() {
    return bagMap.keySet().stream()
        .filter(this::hasGold)
        .count();
  }

  public long part2() {
    return fullBagSize("shiny gold") - 1;
  }

}
