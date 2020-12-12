package com.github.berry120.adventofcode_2020;

import java.awt.Point;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day11 {

  private final String[] lines;

  public Day11(String input) {
    lines = input.split("\n");
  }

  private String turnL(String dir, int degrees) {
    while (degrees > 0) {
      degrees -= 90;
      switch (dir) {
        case "E" -> dir = "N";
        case "N" -> dir = "W";
        case "W" -> dir = "S";
        case "S" -> dir = "E";
      }
    }
    return dir;
  }

  private String turnR(String dir, int degrees) {
    while (degrees > 0) {
      degrees -= 90;
      switch (dir) {
        case "E" -> dir = "S";
        case "S" -> dir = "W";
        case "W" -> dir = "N";
        case "N" -> dir = "E";
      }
    }
    return dir;
  }


  public int part1() {

    String facing = "E";

    int x = 0, y = 0;

    for (String line : lines) {
      int num = Integer.parseInt(line.substring(1));
      switch (line.substring(0, 1)) {
        case "N" -> y += num;
        case "S" -> y -= num;
        case "E" -> x += num;
        case "W" -> x -= num;
        case "L" -> facing = turnL(facing, num);
        case "R" -> facing = turnR(facing, num);
        case "F" -> {
          switch (facing) {
            case "N" -> y += num;
            case "S" -> y -= num;
            case "E" -> x += num;
            case "W" -> x -= num;
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
