package com.github.berry120.adventofcode_2022;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Day11 {

  private List<Monkey> monkeys;

  public Day11(String input) {
    monkeys = new ArrayList<>();
    String[] monkeyStrs = input.split("\n\n");
    for (String monkeyStr : monkeyStrs) {
      String[] lines = monkeyStr.split("\n");

      List<Long> startingItems = new ArrayList<>();
      String startingStr = lines[1];
      startingStr = startingStr.split(": ")[1].trim();
      for (String startingStrNum : startingStr.split(", ")) {
        startingItems.add(Long.parseLong(startingStrNum));
      }

      Function<Long, Long> operation;
      String operationStr = lines[2].split("= ")[1].trim();
      if (operationStr.contains("+")) {
        String firstOp = operationStr.split("\\+")[0].trim();
        String secondOp = operationStr.split("\\+")[1].trim();
        if (firstOp.equals("old") && secondOp.equals("old")) {
          operation = x -> x + x;
        } else if (firstOp.equals("old") && !secondOp.equals("old")) {
          operation = x -> x + Long.parseLong(secondOp);
        } else if (!firstOp.equals("old") && secondOp.equals("old")) {
          operation = x -> Long.parseLong(firstOp) + x;
        } else {
          operation = x -> Long.parseLong(firstOp) + Long.parseLong(secondOp);
        }
      } else if (operationStr.contains("*")) {
        String firstOp = operationStr.split("\\*")[0].trim();
        String secondOp = operationStr.split("\\*")[1].trim();
        if (firstOp.equals("old") && secondOp.equals("old")) {
          operation = x -> x * x;
        } else if (firstOp.equals("old") && !secondOp.equals("old")) {
          operation = x -> x * Long.parseLong(secondOp);
        } else if (!firstOp.equals("old") && secondOp.equals("old")) {
          operation = x -> Long.parseLong(firstOp) * x;
        } else {
          operation = x -> Long.parseLong(firstOp) * Long.parseLong(secondOp);
        }
      } else {
        throw new RuntimeException();
      }

      int divBy = Integer.parseInt(lines[3].split("by ")[1]);
      int trueMonkey = Integer.parseInt(lines[4].split("monkey ")[1]);
      int falseMonkey = Integer.parseInt(lines[5].split("monkey ")[1]);
      Function<Long, Integer> throwTo = x ->
          x % divBy == 0 ? trueMonkey : falseMonkey;

      monkeys.add(new Monkey(startingItems, operation, divBy, throwTo, 0));
    }
  }

  public long part1() {
    for (int r = 0; r < 20; r++) {
      for (Monkey monkey : monkeys) {
        monkey.setInspectedItems(monkey.getInspectedItems() + monkey.getItems().size());

        List<Long> newItems = monkey.getItems().stream().map(monkey.getOperation()::apply)
            .map(x -> x / 3)
            .toList();
        List<Integer> toThrow = newItems.stream().map(monkey.getThrowTo()::apply).toList();

        for (int i = 0; i < toThrow.size(); i++) {
          monkeys.get(toThrow.get(i)).getItems().add(newItems.get(i));
        }

        monkey.setItems(new ArrayList<>());
      }
    }

    return monkeys.stream().mapToLong(x -> x.getInspectedItems()).sorted().skip(monkeys.size() - 2)
        .reduce(1L, (a, b) -> a * b);
  }

  public long part2() {
    long ceiling = monkeys.stream().mapToInt(m -> m.getDivBy()).reduce(1, (x, y) -> x * y);

    for (int r = 0; r < 10000; r++) {
      for (Monkey monkey : monkeys) {
        monkey.setInspectedItems(monkey.getInspectedItems() + monkey.getItems().size());

        List<Long> newItems = monkey.getItems().stream().map(monkey.getOperation()::apply)
            .map(n -> n % ceiling)
            .toList();
        List<Integer> toThrow = newItems.stream().map(monkey.getThrowTo()::apply).toList();

        for (int i = 0; i < toThrow.size(); i++) {
          monkeys.get(toThrow.get(i)).getItems().add(newItems.get(i));
        }

        monkey.setItems(new ArrayList<>());
      }
    }

    return monkeys.stream().mapToLong(x -> x.getInspectedItems()).sorted().skip(monkeys.size() - 2)
        .reduce(1L, (a, b) -> a * b);
  }

}

@AllArgsConstructor
@Data
class Monkey {

  private List<Long> items;
  private Function<Long, Long> operation;
  private int divBy;
  private Function<Long, Integer> throwTo;
  private long inspectedItems;
}
