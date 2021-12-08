package com.github.berry120.adventofcode_2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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

  public int part1() {
    return 0;
  }

  public long part2() {
    int sum = 0;
    for (Line line : lines) {
      List<String> allParts = Stream.concat(line.input().stream(), line.output().stream()).toList();

      Set<Integer> fullSet = IntStream.rangeClosed(97, 103).boxed().collect(Collectors.toSet());

      Set<Integer> commonLength6 =
          allParts.stream()
              .filter(x -> x.length() == 6)
              .map(this::toSet)
              .reduce((s1, s2) -> s1.stream().filter(s2::contains).collect(Collectors.toSet()))
              .get();

      Set<Integer> cde =
          fullSet.stream().filter(x -> !commonLength6.contains(x)).collect(Collectors.toSet());

      Set<Integer> twoParts =
          allParts.stream().filter(p -> p.length() == 2).findAny().map(this::toSet).get();
      int c = twoParts.stream().filter(cde::contains).findAny().get();

      Set<Integer> fourParts =
          allParts.stream().filter(p -> p.length() == 4).findAny().map(this::toSet).get();
      int e = cde.stream().filter(x -> !fourParts.contains(x)).findAny().get();

      sum +=
          line.output().stream()
              .map(
                  part ->
                      part.length() == 2
                          ? 1
                          : part.length() == 3
                              ? 7
                              : part.length() == 4
                                  ? 4
                                  : part.length() == 5
                                      ? (part.chars().anyMatch(a -> a == e)
                                          ? 2
                                          : part.chars().allMatch(a -> a != c) ? 5 : 3)
                                      : part.length() == 6
                                          ? (part.chars().allMatch(a -> a != c)
                                              ? 6
                                              : part.chars().allMatch(a -> a != e) ? 9 : 0)
                                          : 8)
              .reduce(0, (i1, i2) -> Integer.parseInt("" + i1 + i2));
    }
    return sum;
  }

  private Set<Integer> toSet(String s) {
    return s.chars().boxed().collect(Collectors.toSet());
  }

  record Line(List<String> input, List<String> output) {}
}
