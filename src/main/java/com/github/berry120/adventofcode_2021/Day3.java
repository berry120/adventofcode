package com.github.berry120.adventofcode_2021;

import lombok.AllArgsConstructor;
import lombok.With;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@AllArgsConstructor
public class Day3 {

  private String input;

  public static int getBit(int num, int position) {
    return (num >> position) & 1;
  }

  int mostCommon(String input, Predicate<Tracker> p) {
    Tracker[] trackerList = new Tracker[input.split("\n")[0].length()];
    for (int i = 0; i < trackerList.length; i++) {
      trackerList[i] = new Tracker(0, 0);
    }

    String output = "";
    String[] lines = input.split("\n");
    int lineLength = lines[0].length();
    for (int i = 0; i < lineLength; i++) {
      final int idx = i;
      int num1s = (int) Arrays.stream(lines).filter(s -> s.charAt(idx) == '1').count();
      int num0s = (int) Arrays.stream(lines).filter(s -> s.charAt(idx) == '0').count();
      Tracker t = new Tracker(num1s, num0s);
      if (p.test(t)) {
        output += "1";
      } else {
        output += "0";
      }
    }
    return Integer.parseInt(output, 2);
  }

  public int part1() {
    int val = mostCommon(input, t -> t.ones() >= t.zeros());
    return val * (~val & (int) Math.pow(2, input.split("\n")[0].length()) - 1);
  }

  public int part2() {
    return part2(t -> t.ones() >= t.zeros()) * part2(t -> t.zeros() > t.ones());
  }

  public int part2(Predicate<Tracker> predicate) {
    List<String> remaining = Arrays.asList(input.split("\n"));
    for (int i = 0; i < remaining.get(0).length(); i++) {
      final int idx = i;

      int result = mostCommon(String.join("\n", remaining), predicate);

      int x = getBit(result, remaining.get(0).length() - 1 - i);
      char c;
      if (x == 1) {
        c = '1';
      } else {
        c = '0';
      }

      remaining = remaining.stream().filter(s -> s.charAt(idx) == c).toList();
      if (remaining.size() == 1) {
        return Integer.parseInt(remaining.get(0), 2);
      }
    }
    throw new RuntimeException();
  }

  @With
  record Tracker(int ones, int zeros) {}
}
