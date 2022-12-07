package com.github.berry120.adventofcode_2022;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Day5 {

  private List<Stack<Character>> stacks;
  private List<Command> commands;

  public Day5(String input) {
    stacks = new ArrayList<>();
    String[] parts = input.split("\n\n");

    String stacks = parts[0];
    String[] stackLines = stacks.split("\n");
    for (int i = stackLines.length - 2; i >= 0; i--) {
      String line = stackLines[i];
      for (int j = 1; j < line.length(); j += 4) {
        if (line.charAt(j) != ' ') {
          if (i == stackLines.length - 2) {
            this.stacks.add(new Stack<>());
          }
          this.stacks.get((j - 1) / 4).push(line.charAt(j));
        }
      }
    }

    commands = new ArrayList<>();
    String[] commandLines = parts[1].split("\n");
    for (String command : commandLines) {
      String[] commandParts = command.split(" ");
      commands.add(
          new Command(
              Integer.parseInt(commandParts[1]),
              Integer.parseInt(commandParts[3]) - 1,
              Integer.parseInt(commandParts[5]) - 1));
    }
  }

  public String part1() {
    for (Command command : commands) {
      for (int i = 0; i < command.num(); i++) {
        Character c = stacks.get(command.from()).pop();
        stacks.get(command.to()).push(c);
      }
    }
    return stacks.stream().map(x -> Character.toString(x.peek())).collect(Collectors.joining());
  }

  public String part2() {
    for (Command command : commands) {
      List<Character> chars = new ArrayList<>();
      for (int i = 0; i < command.num(); i++) {
        chars.add(stacks.get(command.from()).pop());
      }
      Collections.reverse(chars);
      for (Character c : chars) stacks.get(command.to()).push(c);
    }
    return stacks.stream().map(x -> Character.toString(x.peek())).collect(Collectors.joining());
  }
}

record Command(int num, int from, int to) {}
