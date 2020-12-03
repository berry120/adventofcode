package com.github.berry120.adventofcode_2020;

import java.util.stream.IntStream;

public class Day3 {

  private final String[] lines;

  public Day3(String input) {
    lines = input.split("\n");
  }

  public long part1() {
    return trees(3, 1);
  }

  public long part2() {
    return trees(1, 1) *
           trees(3, 1) *
           trees(5, 1) *
           trees(7, 1) *
           trees(1, 2);
  }

  public long trees(int hInc, int vInc) {
     return IntStream.rangeClosed(0, lines.length/vInc - 1)
        .mapToObj(i -> lines[i * vInc].charAt((i * hInc) % lines[0].length()))
        .filter(c -> c == '#')
        .count();
  }

}
