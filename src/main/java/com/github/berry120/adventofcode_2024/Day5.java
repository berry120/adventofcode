package com.github.berry120.adventofcode_2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;

public class Day5 {

  private List<int[]> rules;
  private List<List<Integer>> sequences;

  public Day5(String input) {
    rules =
        Arrays.stream(input.split("\n\n")[0].split("\n"))
            .map(
                rule ->
                    new int[] {
                      Integer.parseInt(rule.split("\\|")[0]), Integer.parseInt(rule.split("\\|")[1])
                    })
            .toList();

    sequences =
        Arrays.stream(input.split("\n\n")[1].split("\n"))
            .map(sequence -> Arrays.stream(sequence.split(",")).map(Integer::parseInt).toList())
            .toList();
  }

  public long part1() {
    return sequences.stream()
        .filter(seq -> valid(rules, seq))
        .mapToInt(seq -> seq.get(seq.size() / 2))
        .sum();
  }

  public long part2() {
    return sequences.stream()
        .filter(s -> !valid(rules, s))
        .mapToInt(
            sequence -> {
              List<Integer> mutableSequence = new ArrayList<>(sequence);
              while (!valid(rules, mutableSequence)) {
                rules.stream()
                    .filter(
                        rule ->
                            mutableSequence.contains(rule[0]) && mutableSequence.contains(rule[1]))
                    .forEach(
                        rule ->
                            Collections.swap(
                                mutableSequence,
                                Math.max(
                                    mutableSequence.indexOf(rule[0]),
                                    mutableSequence.indexOf(rule[1])),
                                mutableSequence.indexOf(rule[1])));
              }
              return mutableSequence.get(mutableSequence.size() / 2);
            })
        .sum();
  }

  private boolean valid(List<int[]> rules, List<Integer> sequence) {
    return rules.stream()
        .allMatch(
            rule ->
                !(sequence.contains(rule[0]) && sequence.contains(rule[1]))
                    || sequence.indexOf(rule[0]) < sequence.indexOf(rule[1]));
  }
}
