package com.github.berry120.adventofcode_2024;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day2 {

  private String input;

  public long part1() {
    return input
        .lines()
        .map(line -> Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).boxed().toList())
        .filter(
            nums ->
                nums.stream()
                    .gather(Gatherers.windowSliding(2))
                    .allMatch(
                        l ->
                            ValueRange.of(1, 3).isValidValue(Math.abs(l.getFirst() - l.getLast()))))
        .filter(
            nums ->
                nums.equals(nums.stream().sorted().toList())
                    || nums.equals(nums.stream().sorted(Collections.reverseOrder()).toList()))
        .count();
  }

  public long part2() {
    return input
        .lines()
        .map(line -> Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).boxed().toList())
        .map(
            nums ->
                IntStream.range(0, nums.size())
                    .mapToObj(
                        i ->
                            IntStream.range(0, nums.size())
                                .filter(r -> r != i)
                                .mapToObj(nums::get)
                                .toList())
                    .toList())
        .filter(
            list ->
                list.stream()
                    .anyMatch(
                        nums ->
                            nums.stream()
                                    .gather(Gatherers.windowSliding(2))
                                    .allMatch(
                                        l ->
                                            Math.abs(l.getFirst() - l.getLast()) > 0
                                                && Math.abs(l.getFirst() - l.getLast()) < 4)
                                && (nums.equals(nums.stream().sorted().toList())
                                    || nums.equals(
                                        nums.stream()
                                            .sorted(Collections.reverseOrder())
                                            .toList()))))
        .count();
  }
}
