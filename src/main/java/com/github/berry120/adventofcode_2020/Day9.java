package com.github.berry120.adventofcode_2020;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Day9 {

  private final long[] nums;

  public Day9(String input) {
    nums = input.lines().mapToLong(Long::parseLong).toArray();
  }

  public long part1() {
    int lookBack = 25;

    return IntStream.range(lookBack, nums.length)
        .filter(x -> IntStream.range(x - lookBack, x)
            .allMatch(i -> IntStream.range(i + 1, x)
                .noneMatch(j -> nums[i] + nums[j] == nums[x])
            )
        )
        .mapToLong(x -> nums[x])
        .findFirst()
        .orElseThrow();
  }

  public long part2() {
    long sumTo = part1();

    return LongStream.range(0, nums.length)
        .flatMap(i ->
            LongStream.rangeClosed(i + 2, nums.length)
                .mapToObj(j -> Arrays.stream(nums, (int) i, (int) j).summaryStatistics())
                .takeWhile(j -> j.getSum() <= sumTo)
                .filter(j -> j.getSum() == sumTo)
                .mapToLong(s -> s.getMax() + s.getMin())
        )
        .findFirst()
        .orElseThrow();
  }

}
