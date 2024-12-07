package com.github.berry120.adventofcode_2024;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day7 {
  private String input;

  public long part1() {
    long ret = 0;
    for (String line : input.split("\n")) {
      long result = Long.parseLong(line.split(":")[0]);
      List<Integer> parts =
          Arrays.stream(line.split(":")[1].trim().split(" ")).map(Integer::parseInt).toList();

      for (int i = 0; i < 1 << parts.size() - 1; i++) {
        long resultBuilder = parts.getFirst();
        for (int numIdx = 1; numIdx < parts.size(); numIdx++) {
          resultBuilder =
              (i >> (numIdx - 1) & 1) == 0
                  ? resultBuilder + parts.get(numIdx)
                  : resultBuilder * parts.get(numIdx);
        }
        if (resultBuilder == result) {
          ret += result;
          break;
        }
      }
    }
    return ret;
  }

  public long part2() {
    long ret = 0;
    for (String line : input.split("\n")) {
      long result = Long.parseLong(line.split(":")[0]);
      List<Integer> parts =
          Arrays.stream(line.split(":")[1].trim().split(" ")).map(Integer::parseInt).toList();

      for (int i = 0; i < Math.pow(3, parts.size() - 1); i++) {
        long resultBuilder = parts.getFirst();
        for (int numIdx = 1; numIdx < parts.size(); numIdx++) {
          resultBuilder =
              switch(i / (numIdx == 1 ? 1 : (int) (Math.pow(3, (numIdx - 1)))) % 3) {
                case 0 -> resultBuilder + parts.get(numIdx);
                case 1 -> Long.parseLong("" + resultBuilder + parts.get(numIdx));
                default -> resultBuilder * parts.get(numIdx);
              };
        }
        if (resultBuilder == result) {
          ret += result;
          break;
        }
      }
    }
    return ret;
  }
}
