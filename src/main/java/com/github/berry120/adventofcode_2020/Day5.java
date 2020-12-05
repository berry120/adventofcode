package com.github.berry120.adventofcode_2020;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day5 {

  private final String input;

  private int seatNum(String line) {
    return Integer.parseInt(line.replaceAll("[BR]", "1").replaceAll("[FL]", "0"), 2);
  }

  public int part1() {
    return input.lines()
        .mapToInt(this::seatNum)
        .max()
        .orElseThrow();
  }

  public long part2() {
    Set<Integer> allSeats = input.lines().map(this::seatNum).collect(Collectors.toSet());

    return IntStream
        .rangeClosed(allSeats.stream().min(Comparator.naturalOrder()).orElseThrow(), part1())
        .filter(s -> !allSeats.contains(s))
        .findAny()
        .orElseThrow();
  }

}
