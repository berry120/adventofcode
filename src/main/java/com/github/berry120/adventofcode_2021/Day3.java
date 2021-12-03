package com.github.berry120.adventofcode_2021;

import lombok.AllArgsConstructor;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@AllArgsConstructor
public class Day3 {

  private String input;

  static String mostCommon(String input, Predicate<Tracker> p) {
    Tracker[] trackerList = new Tracker[input.split("\n")[0].length()];
    for (int i = 0; i < trackerList.length; i++) {
      trackerList[i] = new Tracker(0, 0);
    }

    for (String line : input.split("\n")) {
      for (int i = 0; i < line.length(); i++) {
        char c = line.charAt(i);
        if (c == '1') {
          trackerList[i] = new Tracker(trackerList[i].ones() + 1, trackerList[i].zeros());
        } else {
          trackerList[i] = new Tracker(trackerList[i].ones(), trackerList[i].zeros() + 1);
        }
      }
    }

    String result = "";
    for (Tracker tracker : trackerList) {
      if (p.test(tracker)) {
        result += "1";
      } else {
        result += "0";
      }
    }
    return result;
  }

  public int part1() {
    String str = mostCommon(input, t -> t.ones() >= t.zeros());
    int val = Integer.parseInt(str, 2);
    return val * (~val & (int) Math.pow(2, input.split("\n")[0].length()) - 1);
  }

  public int part2() {
    return part2(t -> t.ones() >= t.zeros()) * part2(t -> t.zeros() > t.ones());
  }

  public int part2(Predicate<Tracker> predicate) {
    List<String> remaining = Arrays.asList(input.split("\n"));
    for (int i = 0; i < input.split("\n")[0].length(); i++) {
      final int idx = i;
      char c = mostCommon(String.join("\n", remaining), predicate).charAt(i);
      remaining = remaining.stream().filter(s -> s.charAt(idx) == c).toList();
      if (remaining.size() == 1) {
        return Integer.parseInt(remaining.get(0), 2);
      }
    }
    throw new RuntimeException();
  }

  record Tracker(int ones, int zeros) {}
}
