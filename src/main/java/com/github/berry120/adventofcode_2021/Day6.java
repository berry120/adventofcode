package com.github.berry120.adventofcode_2021;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Day6 {

  private final Map<Integer, Long> freqMap;

  public Day6(String str) {
    freqMap =
        Arrays.stream(str.split(","))
            .collect(Collectors.groupingBy(Integer::parseInt, Collectors.counting()));
  }

  public long part1() {
    return calc(80);
  }

  public long part2() {
    return calc(256);
  }

  public long calc(int totalDays) {
    Map<Integer, Long> state = new HashMap<>(freqMap);
    for (int days = 0; days < totalDays; days++) {
      long zeroes = state.getOrDefault(0, 0L);
      for (int i = 0; i < 8; i++) {
        state.put(i, state.getOrDefault(i + 1, 0L));
      }
      state.put(6, state.getOrDefault(6, 0L) + zeroes);
      state.put(8, zeroes);
    }
    return state.values().stream().reduce(Long::sum).orElseThrow();
  }
}
