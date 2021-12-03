package com.github.berry120.adventofcode_2021;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

public class Day3 {

  private final List<Integer> nums;
  private final int length;

  public Day3(String input) {
    nums = Arrays.stream(input.split("\n")).map(s -> Integer.parseInt(s, 2)).toList();
    length = Integer.toBinaryString(Collections.max(nums)).length();
  }

  public int part1() {
    return mostCommon(nums, (o, z) -> o >= z) * mostCommon(nums, (o, z) -> o < z);
  }

  public int part2() {
    return ratings((o, z) -> o >= z) * ratings((o, z) -> o < z);
  }

  private int mostCommon(List<Integer> lines, BiPredicate<Long, Long> p) {
    return IntStream.range(0, length).map(idx ->
            p.test(lines.stream().filter(s -> bitAt(s, idx) == 1).count(),
                lines.stream().filter(s -> bitAt(s, idx) == 0).count()) ? 1 : 0)
        .reduce(this::appendBinary).orElseThrow();
  }

  public int ratings(BiPredicate<Long, Long> predicate) {
    List<Integer> remaining = nums;
    for (int i = 0; i < length && remaining.size() > 1; i++) {
      final int idx = i;
      int bit = bitAt(mostCommon(remaining, predicate), idx);
      remaining = remaining.stream().filter(val -> bitAt(val, idx) == bit).toList();
    }
    return remaining.get(0);
  }

  private int appendBinary(int a, int b) {
    int r1 = a << -Integer.numberOfLeadingZeros(b) | b;
    if (b == 0) {
      r1 <<= 1;
    }
    return r1;
  }

  private int bitAt(int val, int i) {
    return (val >> length - 1 - i) & 1;
  }
}
