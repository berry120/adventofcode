package com.github.berry120.adventofcode_2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day8 {

  private final List<Line> lines;

  public Day8(String input) {
    String[] inputLines = input.split("\n");
    lines = new ArrayList<>();
    for (String line : inputLines) {
      String[] inputParts = line.split("\\|")[0].trim().split(" ");
      String[] outputParts = line.split("\\|")[1].trim().split(" ");
      lines.add(new Line(Arrays.asList(inputParts), Arrays.asList(outputParts)));
    }
  }

  private static Set<String> toSet(String s) {
    Set<String> ret = new HashSet<>();
    for (char c : s.toCharArray()) {
      ret.add("" + c);
    }
    return ret;
  }

  public int part1() {
    return 0;
  }

  public long part2() {
    int sum = 0;
    for (Line line : lines) {
      List<String> allParts = Stream.concat(line.input().stream(), line.output().stream()).toList();

      Set<String> fullSet = Set.of("a", "b", "c", "d", "e", "f", "g");

      Set<String> commonLength6 = new HashSet<>(fullSet);
      for (String part : allParts) {
        if (part.length() == 6) {
          commonLength6.retainAll(toSet(part));
        }
      }

      Set<String> cde = new HashSet<>(fullSet);
      cde.removeAll(commonLength6);

      Set<String> twoParts = new HashSet<>();
      for (String part : allParts) {
        if (part.length() == 2) {
          twoParts = toSet(part);
        }
      }

      Set<String> cSet = new HashSet<>(twoParts);
      cSet.retainAll(cde);

      String c = cSet.iterator().next();

      Set<String> fourParts = new HashSet<>();
      for (String part : allParts) {
        if (part.length() == 4) {
          fourParts = toSet(part);
        }
      }

      Set<String> eSet = new HashSet<>(cde);
      eSet.removeAll(fourParts);

      String e = eSet.iterator().next();

      String output = "";
      for (String part : line.output()) {
        if (part.length() == 2) {
          output += 1;
        } else if (part.length() == 3) {
          output += 7;
        } else if (part.length() == 4) {
          output += 4;
        } else if (part.length() == 5) {
          if (part.contains(e)) {
            output += 2;
          } else if (!part.contains(c)) {
            output += 5;
          } else {
            output += 3;
          }
        } else if (part.length() == 6) {
          if (!part.contains(c)) {
            output += 6;
          } else if (!part.contains(e)) {
            output += 9;
          } else {
            output += 0;
          }
        } else if (part.length() == 7) {
          output += 8;
        }
      }
      sum += Integer.parseInt(output);
    }
    return sum;
  }

  record Line(List<String> input, List<String> output) {}
}
