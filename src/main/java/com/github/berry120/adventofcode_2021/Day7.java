package com.github.berry120.adventofcode_2021;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day7 {

  private final List<Integer> nums;

  public Day7(String str) {
    nums = Arrays.stream(str.split(",")).map(Integer::parseInt).toList();
  }

  public int part1() {
    return calc(false);
  }

  public long part2() {
    return calc(true);
  }

  private int calc(boolean triangleCalc) {
    return IntStream.rangeClosed(0, nums.stream().max(Integer::compareTo).get())
        .map(
            fp ->
                nums.stream()
                    .map(p -> Math.abs(fp - p))
                    .map(d -> triangleCalc ? (d * (d + 1)) / 2 : d)
                    .reduce(Integer::sum)
                    .get())
        .min()
        .getAsInt();
  }
}
