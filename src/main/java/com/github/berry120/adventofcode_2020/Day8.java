package com.github.berry120.adventofcode_2020;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day8 {

  private final String input;

  public int part1() {
    return execute(input.split("\n")).getValue();
  }

  public int part2() {
    for (int i = 0; i < input.lines().count(); i++) {
      String[] copy = input.split("\n");
      if (copy[i].startsWith("nop")) {
        copy[i] = "jmp" + copy[i].substring(3);
      } else if (copy[i].startsWith("jmp")) {
        copy[i] = "nop" + copy[i].substring(3);
      }
      if (execute(copy).getKey()) {
        return execute(copy).getValue();
      }
    }
    return -1;
  }

  public SimpleEntry<Boolean, Integer> execute(String[] lines) {
    int acc = 0;
    Set<Integer> visited = new HashSet<>();

    for (int i = 0; i < lines.length; i++) {

      if (!visited.add(i)) {
        return new SimpleEntry<>(false, acc);
      }

      int arg = Integer.parseInt(lines[i].split(" ")[1]);
      if (lines[i].startsWith("acc")) {
        acc += arg;
      } else if (lines[i].startsWith("jmp")) {
        i += arg - 1;
      }
    }
    return new SimpleEntry<>(true, acc);
  }

}
