package com.github.berry120.adventofcode_2020;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day5 {

  private final String[] lines;

  public Day5(String input) {
    lines = input.split("\n");
  }

  private int seatNum(String line) {
    return Integer.parseInt(
        line.replace('B', '1')
            .replace('F', '0')
            .replace('R', '1')
            .replace('L', '0'), 2);
  }

  public int part1() {
    return Arrays.stream(lines)
        .map(this::seatNum)
        .max(Comparator.naturalOrder())
        .orElseThrow();
  }

  public long part2() {
    Set<Integer> seats = IntStream
        .rangeClosed(Arrays.stream(lines)
            .map(this::seatNum)
            .min(Comparator.naturalOrder())
            .orElseThrow(), part1())
        .boxed()
        .collect(Collectors.toSet());

    seats.removeAll(Arrays.stream(lines).map(this::seatNum).collect(Collectors.toSet()));

    return seats.iterator().next();
  }

}
