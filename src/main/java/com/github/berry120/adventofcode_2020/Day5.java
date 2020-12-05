package com.github.berry120.adventofcode_2020;

import java.util.Arrays;
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
        line.replaceAll("[BR]", "1")
            .replaceAll("[FL]", "0"), 2);
  }

  public int part1() {
    return Arrays.stream(lines)
        .mapToInt(this::seatNum)
        .max()
        .orElseThrow();
  }

  public long part2() {
    Set<Integer> seats = IntStream
        .rangeClosed(Arrays.stream(lines)
            .mapToInt(this::seatNum)
            .min()
            .orElseThrow(), part1())
        .boxed()
        .collect(Collectors.toSet());

    seats.removeAll(Arrays.stream(lines).map(this::seatNum).collect(Collectors.toSet()));

    return seats.iterator().next();
  }

}
