package com.github.berry120.adventofcode_2021;

public class Day17 {

  private final int areaMinX;
  private final int areaMaxX;
  private final int areaMinY;
  private final int areaMaxY;

  public Day17(String input) {
    String[] xy = input.split(",");

    String[] xParts = xy[0].trim().substring(2).split("\\.\\.");
    String[] yParts = xy[1].trim().substring(2).split("\\.\\.");

    areaMinX = Integer.parseInt(xParts[0]);
    areaMaxX = Integer.parseInt(xParts[1]);
    areaMinY = Integer.parseInt(yParts[0]);
    areaMaxY = Integer.parseInt(yParts[1]);
  }

  private int highestY(int xStartingVel, int yStartingVel) {
    int highestY = Integer.MIN_VALUE;
    int xVel = xStartingVel;
    int yVel = yStartingVel;
    int xPos = 0;
    int yPos = 0;
    while (true) {
      xPos += xVel;
      yPos += yVel;
      if (yPos > highestY) {
        highestY = yPos;
      }
      if (xVel > 0) {
        xVel--;
      }
      if (xVel < 0) {
        xVel++;
      }
      yVel--;
      if (inArea(xPos, yPos)) {
        return highestY;
      }
      if (yPos < areaMinY) {
        return Integer.MIN_VALUE;
      }
    }
  }

  public int part1() {
    int highestY = 0;

    for (int x = -2000; x < 2000; x++) {
      for (int y = -2000; y < 2000; y++) {
        int hy = highestY(x, y);
        if (hy > highestY) {
          highestY = hy;
        }
      }
    }
    return highestY;
  }

  public int part2() {
    int options = 0;

    for (int x = -2000; x < 2000; x++) {
      for (int y = -2000; y < 2000; y++) {
        int hy = highestY(x, y);
        if (hy > Integer.MIN_VALUE) {
          options++;
        }
      }
    }
    return options;
  }

  private boolean inArea(int x, int y) {
    return x >= areaMinX && x <= areaMaxX && y >= areaMinY && y <= areaMaxY;
  }
}
