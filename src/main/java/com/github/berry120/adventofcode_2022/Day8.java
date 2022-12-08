package com.github.berry120.adventofcode_2022;

import java.awt.Point;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Day8 {

  private final int[][] grid;

  public Day8(String input) {
    String[] lines = input.split("\n");
    grid = new int[lines[0].length()][lines.length];
    for (int i = 0; i < lines[0].length(); i++) {
      for (int j = 0; j < lines.length; j++) {
        grid[i][j] = Integer.parseInt(Character.toString(lines[j].charAt(i)));
      }
    }
  }

  public long part1() {
    return IntStream.range(1, grid[0].length - 1)
            .boxed()
            .flatMap(i -> IntStream.range(1, grid.length - 1).mapToObj(j -> new Point(i, j)))
            .filter(
                p ->
                    IntStream.range(0, p.x).map(x -> grid[x][p.y]).allMatch(x -> x < grid[p.x][p.y])
                        || IntStream.range(p.x + 1, grid[0].length)
                            .map(x -> grid[x][p.y])
                            .allMatch(x -> x < grid[p.x][p.y])
                        || Arrays.stream(grid[p.x], 0, p.y)
                            .allMatch(x -> x < grid[p.x][p.y])
                        || Arrays.stream(grid[p.x], p.y + 1, grid.length)
                            .allMatch(x -> x < grid[p.x][p.y]))
            .count()
        + grid[0].length * 2
        + grid.length * 2
        - 4;
  }

  public long part2() {
    return IntStream.range(0, grid[0].length)
        .boxed()
        .flatMap(i -> IntStream.range(0, grid.length).mapToObj(j -> new Point(i, j)))
        .mapToLong(
            p ->
                (1
                        + IntStream.iterate(p.x - 1, x -> x > 0, x -> x - 1)
                            .takeWhile(x -> grid[x][p.y] < grid[p.x][p.y])
                            .count())
                    * (1
                        + IntStream.iterate(p.x + 1, x -> x < grid[0].length - 1, x -> x + 1)
                            .takeWhile(x -> grid[x][p.y] < grid[p.x][p.y])
                            .count())
                    * (1
                        + IntStream.iterate(p.y - 1, y -> y > 0, y -> y - 1)
                            .takeWhile(y -> grid[p.x][y] < grid[p.x][p.y])
                            .count())
                    * (1
                        + IntStream.iterate(p.y + 1, y -> y < grid.length - 1, y -> y + 1)
                            .takeWhile(y -> grid[p.x][y] < grid[p.x][p.y])
                            .count()))
        .max()
        .orElse(0);
  }
}
