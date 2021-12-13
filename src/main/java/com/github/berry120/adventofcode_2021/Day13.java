package com.github.berry120.adventofcode_2021;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day13 {

  private final List<Point> points;
  private final List<String> folds;

  public Day13(String input) {
    points =
        Arrays.stream(input.split("\n\n")[0].split("\n"))
            .map(
                line ->
                    new Point(
                        Integer.parseInt(line.split(",")[0]), Integer.parseInt(line.split(",")[1])))
            .toList();
    folds = Arrays.stream(input.split("\n\n")[1].split("\n")).map(s -> s.substring(11)).toList();
  }

  public String part2() {
    Set<Point> pointSet = new HashSet<>(points);
    for (String line : folds) {
      int lineNum = Integer.parseInt(line.split("=")[1]);
      Set<Point> newPointSet = new HashSet<>();
      if (line.startsWith("x")) {
        for (Point point : pointSet) {
          if (point.x() > lineNum) {
            int diff = point.x() - lineNum;
            int x = lineNum - diff;
            newPointSet.add(new Point(x, point.y()));
          } else {
            newPointSet.add(new Point(point.x(), point.y()));
          }
        }
      } else {
        for (Point point : pointSet) {
          if (point.y() > lineNum) {
            int diff = point.y() - lineNum;
            int y = lineNum - diff;
            newPointSet.add(new Point(point.x(), y));
          } else {
            newPointSet.add(new Point(point.x(), point.y()));
          }
        }
      }
      pointSet = newPointSet;
    }
    return toString(pointSet);
  }

  private String toString(Set<Point> pointSet) {
    StringBuilder ret = new StringBuilder();
    int maxX = pointSet.stream().map(p -> p.x()).max(Integer::compare).get();
    int maxY = pointSet.stream().map(p -> p.y()).max(Integer::compare).get();

    for (int y = 0; y <= maxY; y++) {
      for (int x = 0; x <= maxX; x++) {
        if (pointSet.contains(new Point(x, y))) {
          ret.append("#");
        } else {
          ret.append(" ");
        }
      }
      ret.append("\n");
    }
    return ret.toString().trim();
  }

  public int part1() {
    Set<Point> pointSet = new HashSet<>(points);
    String line = folds.get(0);
    int lineNum = Integer.parseInt(line.split("=")[1]);
    Set<Point> newPointSet = new HashSet<>();
    if (line.startsWith("x")) {
      for (Point point : pointSet) {
        if (point.x() > lineNum) {
          int diff = point.x() - lineNum;
          int x = lineNum - diff;
          newPointSet.add(new Point(x, point.y()));
        } else {
          newPointSet.add(new Point(point.x(), point.y()));
        }
      }
    } else {
      for (Point point : pointSet) {
        if (point.y() > lineNum) {
          int diff = point.y() - lineNum;
          int y = lineNum - diff;
          newPointSet.add(new Point(point.x(), y));
        } else {
          newPointSet.add(new Point(point.x(), point.y()));
        }
      }
    }
    return newPointSet.size();
  }

  record Point(int x, int y) {}
}
