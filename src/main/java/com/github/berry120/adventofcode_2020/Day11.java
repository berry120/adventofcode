package com.github.berry120.adventofcode_2020;

import java.awt.Point;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day11 {

  private final String[] lines;

  public Day11(String input) {
    lines = input.split("\n");
  }

  public int part1() {
    int facing = 1;
    int x = 0, y = 0;

    for (String line : lines) {
      int num = Integer.parseInt(line.substring(1));
      switch (line.substring(0, 1)) {
        case "N" -> y += num;
        case "S" -> y -= num;
        case "E" -> x += num;
        case "W" -> x -= num;
        case "L" -> facing = (facing + 3 * num / 90) % 4;
        case "R" -> facing = (facing + num / 90) % 4;
        case "F" -> {
          switch (facing) {
            case 0 -> y += num;
            case 1 -> x += num;
            case 2 -> y -= num;
            case 3 -> x -= num;
          }
        }
      }
    }

    return Math.abs(x) + Math.abs(y);

  }

  public int part2() {

    Point ship = new Point(0, 0);
    Point way = new Point(10, 1);

    for (String line : lines) {
      int num = Integer.parseInt(line.substring(1));
      switch (line.substring(0, 1)) {
        case "N" -> way = new Point(way.x, way.y + num);
        case "S" -> way = new Point(way.x, way.y - num);
        case "E" -> way = new Point(way.x + num, way.y);
        case "W" -> way = new Point(way.x - num, way.y);
        case "L" -> way = switch (num % 360) {
          case 90 -> new Point(-way.y, way.x);
          case 180 -> new Point(-way.x, -way.y);
          case 270 -> new Point(way.y, -way.x);
          default -> null;
        };
        case "R" -> way = switch (num % 360) {
          case 90 -> new Point(way.y, -way.x);
          case 180 -> new Point(-way.x, -way.y);
          case 270 -> new Point(-way.y, way.x);
          default -> null;
        };
        case "F" -> ship = new Point(ship.x + way.x * num, ship.y + way.y * num);
      }
    }
    return Math.abs(ship.x) + Math.abs(ship.y);
  }

}
