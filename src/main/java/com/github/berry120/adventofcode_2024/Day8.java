package com.github.berry120.adventofcode_2024;

import lombok.AllArgsConstructor;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@AllArgsConstructor
public class Day8 {
  private String input;

  public long part1() {
    Map<Point, Integer> freqMap = new HashMap<>();
    Map<Integer, List<Point>> freqMapR = new HashMap<>();
    String[] lines = input.split("\n");
    for (int i = 0; i < lines.length; i++) {
      for (int j = 0; j < lines[i].length(); j++) {
        if (lines[i].charAt(j) != '.') {
          freqMap.put(new Point(i, j), (int) lines[i].charAt(j));
          freqMapR.computeIfAbsent(freqMap.get(new Point(i, j)), _ -> new ArrayList<>());
          freqMapR.get(freqMap.get(new Point(i, j))).add(new Point(i, j));
        }
      }
    }

    Set<Point> antiNodes = new HashSet<>();
    for (Map.Entry<Point, Integer> entry : freqMap.entrySet()) {
      Point point = entry.getKey();
      for (Point otherPoint :
          freqMapR.get(entry.getValue()).stream().filter(p -> !p.equals(point)).toList()) {
        Point np =
            new Point(point.y - ((otherPoint.y - point.y)), point.x - (otherPoint.x - point.x));
        if (np.x >= 0 && np.x < lines[0].length() && np.y >= 0 && np.y < lines.length) {
          antiNodes.add(np);
        }
      }
    }
    return antiNodes.size();
  }

  public long part2() {
    Map<Point, Integer> freqMap = new HashMap<>();
    Map<Integer, List<Point>> freqMapR = new HashMap<>();
    String[] lines = input.split("\n");
    for (int i = 0; i < lines.length; i++) {
      for (int j = 0; j < lines[i].length(); j++) {
        if (lines[i].charAt(j) != '.') {
          freqMap.put(new Point(i, j), (int) lines[i].charAt(j));
          freqMapR.computeIfAbsent(freqMap.get(new Point(i, j)), _ -> new ArrayList<>());
          freqMapR.get(freqMap.get(new Point(i, j))).add(new Point(i, j));
        }
      }
    }

    Set<Point> antiNodes = new HashSet<>();

    for (Map.Entry<Point, Integer> entry : freqMap.entrySet()) {
      Point point = entry.getKey();

      for (Point otherPoint : freqMapR.get(entry.getValue())) {
        int xDiff = otherPoint.x - point.x;
        int yDiff = otherPoint.y - point.y;
        for (int i = 0; i < lines.length; i++) {
          antiNodes.addAll(
              Stream.of(
                      new Point(point.y - yDiff * i, point.x - xDiff * i),
                      new Point(point.y - yDiff * -i, point.x - xDiff * -i))
                  .filter(
                      np ->
                          (np.x >= 0
                              && np.x < lines[0].length()
                              && np.y >= 0
                              && np.y < lines.length))
                  .toList());
        }
      }
    }

    return antiNodes.size();
  }
}
