package com.github.berry120.adventofcode_2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day16 {

  private final String[] fields;
  private final int[] myTicket;
  private final String[] nearbyTickets;

  public Day16(String input) {
    String[] parts = input.split("\n\n");
    fields = parts[0].split("\n");
    myTicket = Arrays.stream(parts[1].split(",")).mapToInt(Integer::parseInt).toArray();
    nearbyTickets = parts[2].split("\n");
  }

  public long part2() {
    List<Range> ranges = new ArrayList<>();
    for (String field : fields) {
      ranges.add(new Range(field));
    }

    List<String> validTickets = new ArrayList<>();

    outer:
    for (String nearby : nearbyTickets) {
      for (String str : nearby.split(",")) {
        Integer num = Integer.parseInt(str);
        if (!isValid(num, ranges)) {
          continue outer;
        }
      }
      validTickets.add(nearby);
    }

    Map<Range, List<Integer>> possibilities = new HashMap<>();

    for (Range range : ranges) {
      List<Integer> list = new ArrayList<>();
      for (int i = 0; i < ranges.size(); i++) {
        list.add(i);
      }
      possibilities.put(range, list);
    }

    for (String validTicket : validTickets) {
      String[] parts = validTicket.split(",");
      for (int i = 0; i < parts.length; i++) {
        int num = Integer.parseInt(parts[i].trim());
        for (Range r : ranges) {
          if (!r.inRange(num)) {
            possibilities.get(r).remove((Object) i);
          }
        }
      }
    }


    while (!allOne(possibilities.values())) {
      int toRemove = getOne(possibilities.values());
      System.out.println("To remove: " + toRemove);
      for (Range r : possibilities.keySet()) {
        if (possibilities.get(r).size() > 1) {
          possibilities.get(r).remove((Object) toRemove);
        }
      }
      removed.add(toRemove);
    }

    System.out.println(possibilities.entrySet().stream()
    .filter(e -> e.getKey().name.startsWith("departure")).mapToLong(e -> myTicket[e.getValue().get(0)]).reduce((a,b)-> a*b));

    System.out.println(possibilities.entrySet().stream().filter(e -> e.getKey().name.startsWith("departure")).map(e -> e.getKey() + ": " + myTicket[e.getValue().get(0)]).collect(Collectors.toList()));

    return 0;
  }

  List<Integer> removed = new ArrayList<>();

  boolean allOne(Collection<List<Integer>> values) {
    for (List<Integer> lists : values) {
      if (lists.size() != 1) {
        return false;
      }
    }
    return true;
  }

  int getOne(Collection<List<Integer>> values) {
    for (List<Integer> lists : values) {
      if (lists.size() == 1) {
        int ret = lists.get(0);
        if(!removed.contains(ret)) {
          return ret;
        }
      }
    }
    throw new AssertionError();
  }

  boolean isValid(int num, List<Range> ranges) {
    for (Range range : ranges) {
      if (range.inRange(num)) {
        return true;
      }
    }
    return false;
  }

  public long part1() {
    return 0;
  }

  static class Range {

    String name;

    int lower;
    int upper;

    int lower2;
    int upper2;

    Range(String line) {
      name = line.split(":")[0].trim();
      line = line.split(":")[1];
      String[] ranges = line.split("or");
      lower = Integer.parseInt(ranges[0].split("-")[0].trim());
      upper = Integer.parseInt(ranges[0].split("-")[1].trim());
      lower2 = Integer.parseInt(ranges[1].split("-")[0].trim());
      upper2 = Integer.parseInt(ranges[1].split("-")[1].trim());
    }

    boolean inRange(int num) {
      return (num <= upper && num >= lower) || (num <= upper2 && num >= lower2);
    }

    public String toString() {
      return name;
    }
  }

}
