package com.github.berry120.adventofcode_2024;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day12 {
  private final char[][] chars;

  public Day12(String input) {
    String[] lines = input.split("\n");
    chars = new char[lines[0].length()][lines.length];
    for (int x = 0; x < lines.length; x++) {
      for (int y = 0; y < lines[0].length(); y++) {
        chars[x][y] = lines[y].charAt(x);
      }
    }
  }

  public long part1() {
    int price = 0;
    for (Set<Point> region : findRegions(chars)) {
      Point first = region.iterator().next();
      char c = chars[first.x][first.y];
      int fence = 0;
      for (Point p : region) {
        if (p.x == 0 || chars[p.x - 1][p.y] != c) {
          fence++;
        }
        if (p.x == chars[0].length - 1 || chars[p.x + 1][p.y] != c) {
          fence++;
        }
        if (p.y == 0 || chars[p.x][p.y - 1] != c) {
          fence++;
        }
        if (p.y == chars.length - 1 || chars[p.x][p.y + 1] != c) {
          fence++;
        }
      }
      price += (region.size() * fence);
    }

    return price;
  }

  public long part2() {
    return findRegions(chars).stream().mapToInt(region -> region.size() * numSides(region)).sum();
  }

  private int numSides(Set<Point> region) {
    Set<Point> hUpFences = new HashSet<>();
    Set<Point> hDownFences = new HashSet<>();
    Set<Point> vUpFences = new HashSet<>();
    Set<Point> vDownFences = new HashSet<>();

    for (Point p : region) {
      if (!region.contains(new Point(p.x - 1, p.y))) hUpFences.add(new Point(p.x, p.y));
      if (!region.contains(new Point(p.x + 1, p.y))) hDownFences.add(new Point(p.x + 1, p.y));
      if (!region.contains(new Point(p.x, p.y - 1))) vUpFences.add(new Point(p.x, p.y));
      if (!region.contains(new Point(p.x, p.y + 1))) vDownFences.add(new Point(p.x, p.y + 1));
    }

    return getFences(vUpFences, true)
        + getFences(vDownFences, true)
        + getFences(hUpFences, false)
        + getFences(hDownFences, false);
  }

  private int getFences(Set<Point> vUpFences, boolean y) {
    int fences = 0;
    var vUpFencesByY = vUpFences.stream().collect(Collectors.groupingBy(p -> y ? p.y : p.x));
    for (List<Integer> pList :
        vUpFencesByY.values().stream()
            .map(l -> l.stream().map(p -> y ? p.x : p.y).sorted().toList())
            .toList()) {
      int last = Integer.MIN_VALUE;
      for (int n : pList) {
        if (n != last + 1) {
          fences++;
        }
        last = n;
      }
    }
    return fences;
  }

  private Set<Set<Point>> findRegions(char[][] grid) {
    Set<Set<Point>> regions = new HashSet<>();
    for (int x = 0; x < grid[0].length; x++) {
      for (int y = 0; y < grid.length; y++) {
        final int fx = x;
        final int fy = y;
        if (regions.stream()
            .flatMap(Collection::stream)
            .filter(p -> p.equals(new Point(fx, fy)))
            .findAny()
            .isEmpty()) regions.add(findRegion(grid, new Point(x, y)));
      }
    }
    return regions;
  }

  private Set<Point> findRegion(char[][] grid, Point p) {
    return findRegion(grid, p, new ArrayList<>());
  }

  private Set<Point> findRegion(char[][] grid, Point p, List<Point> covered) {
    if (covered.contains(p)) {
      return Set.of();
    }
    Set<Point> ret = new HashSet<>();
    char c = grid[p.x][p.y];
    ret.add(p);
    covered.add(p);
    if (p.x > 0 && c == grid[p.x - 1][p.y]) {
      ret.addAll(findRegion(grid, new Point(p.x - 1, p.y), covered));
    }
    if (p.x < grid[0].length - 1 && c == grid[p.x + 1][p.y]) {
      ret.addAll(findRegion(grid, new Point(p.x + 1, p.y), covered));
    }
    if (p.y > 0 && c == grid[p.x][p.y - 1]) {
      ret.addAll(findRegion(grid, new Point(p.x, p.y - 1), covered));
    }
    if (p.y < grid.length - 1 && c == grid[p.x][p.y + 1]) {
      ret.addAll(findRegion(grid, new Point(p.x, p.y + 1), covered));
    }
    return ret;
  }
}
