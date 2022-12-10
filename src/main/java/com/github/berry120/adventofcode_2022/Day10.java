package com.github.berry120.adventofcode_2022;

import java.util.ArrayList;
import java.util.List;

public class Day10 {

  private final List<String> commands;

  public Day10(String input) {
    commands = new ArrayList<>();
    for (String command : input.split("\n")) {
      if (command.startsWith("addx")) {
        commands.add("noop");
      }
      commands.add(command);
    }
  }

  public int part1() {
    int nextCycleToExamine = 20;
    int register = 1;
    int sum = 0;

    for (int i = 1; i < commands.size(); i++) {
      if (i == nextCycleToExamine) {
        sum += i * register;
        nextCycleToExamine += 40;
      }

      if (commands.get(i - 1).startsWith("addx")) {
        register += Integer.parseInt(commands.get(i - 1).split(" ")[1]);
      }

    }
    return sum;
  }

  public String part2() {
    StringBuilder screen = new StringBuilder();
    int spritePos = 1;

    for (int i = 0; i < commands.size(); i++) {
      screen.append((spritePos - 1 == i % 40 || spritePos == i % 40
          || spritePos + 1 == i % 40) ? '#' : '.');

      if (commands.get(i).startsWith("addx")) {
        spritePos += Integer.parseInt(commands.get(i).split(" ")[1]);
      }

    }

    for (int c = screen.length(); c >= 40; c -= 40) {
      screen.insert(c, "\n");
    }
    return screen.toString();
  }
}
