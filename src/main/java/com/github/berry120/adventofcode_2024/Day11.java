package com.github.berry120.adventofcode_2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day11 {
  private String input;

  record Stone(long num, long qty) {}

  public long part1() {
    return numStones(25);
  }

  public long part2() {
    return numStones(75);
  }

  public long numStones(int iter) {
    List<Stone> stones =
        new ArrayList<>(
            Arrays.stream(input.split(" ")).map(num -> new Stone(Long.parseLong(num), 1)).toList());

    for (int i = 0; i < iter; i++) {
      List<Stone> newStones = new ArrayList<>();

      for (Stone stone : stones) {
        int numDigits = (int) Math.log10(stone.num) + 1;
        if (stone.num == 0) {
          newStones.add(new Stone(1, stone.qty));
        } else if (numDigits % 2 == 0) {
          newStones.add(new Stone(stone.num / (long) (Math.pow(10, (numDigits / 2))), stone.qty()));
          newStones.add(new Stone(stone.num % (long) (Math.pow(10, (numDigits / 2))), stone.qty()));
        } else {
          newStones.add(new Stone(stone.num * 2024, stone.qty()));
        }
      }

      stones =
          newStones.stream().collect(Collectors.groupingBy(Stone::num)).entrySet().stream()
              .map(
                  entry ->
                      new Stone(
                          entry.getKey(), entry.getValue().stream().mapToLong(Stone::qty).sum()))
              .toList();
    }

    return stones.stream().mapToLong(Stone::qty).sum();
  }
}
