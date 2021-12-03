package com.github.berry120.adventofcode_2021;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

public class Day3 {

  private List<String> lines;

  public Day3(String input) {
    lines = Arrays.asList(input.split("\n"));
  }

  private int mostCommon(List<String> lines, BiPredicate<Long, Long> p) {
    return IntStream.range(0, lines.get(0).length())
        .map(idx -> p.test(lines.stream().filter(s -> s.charAt(idx) == '1').count(),
            lines.stream().filter(s -> s.charAt(idx) == '0').count()) ? 1 : 0)
        .reduce(this::appendBinary)
        .orElseThrow();
  }

  private int appendBinary(int a, int b) {
    int r1 = a << -Integer.numberOfLeadingZeros(b) | b;
    if (b == 0) {
      r1 <<= 1;
    }
    return r1;
  }

  public int part1() {
    return mostCommon(lines, (o, z) -> o >= z) * mostCommon(lines, (o, z) -> o < z);
  }

  public int part2() {
    return part2((o, z) -> o >= z) * part2((o, z) -> o < z);
  }

  public int part2(BiPredicate<Long, Long> predicate) {
    List<String> remaining = lines;
    for (int i = 0; i < remaining.get(0).length(); i++) {
      final int idx = i;

      char c = ((mostCommon(remaining, predicate)
                 >> remaining.get(0).length() - 1 - i)
                & 1) == 1 ? '1' : '0';

      remaining = remaining.stream().filter(s -> s.charAt(idx) == c).toList();
      if (remaining.size() == 1) {
        return Integer.parseInt(remaining.get(0), 2);
      }
    }
    throw new RuntimeException();
  }

}
