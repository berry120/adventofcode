package com.github.berry120.adventofcode_2020;

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
        case "E":
          dir = "N";
          break;
        case "N":
          dir = "W";
          break;
        case "W":
          dir = "S";
          break;
        case "S":
          dir = "E";
          break;
      }
    }
    return dir;
  }

  private String turnR(String dir, int degrees) {
    while (degrees > 0) {
      degrees -= 90;
      switch (dir) {
        case "E":
          dir = "S";
          break;
        case "S":
          dir = "W";
          break;
        case "W":
          dir = "N";
          break;
        case "N":
          dir = "E";
          break;
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
        case "N": {
          y += num;
          break;
        }
        case "S": {
          y -= num;
          break;
        }
        case "E": {
          x += num;
          break;
        }
        case "W": {
          x -= num;
          break;
        }
        case "L": {
          facing = turnL(facing, num);
          break;
        }
        case "R": {
          facing = turnR(facing, num);
          break;
        }
        case "F": {
          switch (facing) {
            case "N": {
              y += num;
              break;
            }
            case "S": {
              y -= num;
              break;
            }
            case "E": {
              x += num;
              break;
            }
            case "W": {
              x -= num;
              break;
            }
            default: {
              throw new AssertionError(facing);
            }
          }
          break;
        }
        default: {
          throw new AssertionError(line.substring(0, 1));
        }

      }
    }

    return Math.abs(x) + Math.abs(y);

  }

  public int part2() {

    int shipX = 0, shipY = 0;
    int wayX = 10, wayY = 1;

    for (String line : lines) {
      int num = Integer.parseInt(line.substring(1));
      switch (line.substring(0, 1)) {
        case "N": {
          wayY += num;
          break;
        }
        case "S": {
          wayY -= num;
          break;
        }
        case "E": {
          wayX += num;
          break;
        }
        case "W": {
          wayX -= num;
          break;
        }
        case "L": {
          while (num > 0) {
            num -= 90;
            int temp = wayY;
            wayY = wayX;
            wayX = -temp;
          }
          break;
        }
        case "R": {
          while (num > 0) {
            num -= 90;
            int temp = wayY;
            wayY = -wayX;
            wayX = temp;
          }
          break;
        }
        case "F": {
          shipX += (wayX * num);
          shipY += (wayY * num);
          break;
        }
        default: {
          throw new AssertionError(line.substring(0, 1));
        }

      }
    }

    return Math.abs(shipX) + Math.abs(shipY);

  }

}
