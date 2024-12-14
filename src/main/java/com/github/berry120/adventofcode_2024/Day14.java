package com.github.berry120.adventofcode_2024;

import lombok.AllArgsConstructor;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Day14 {
  private String input;

  public int part1() {
    int totalSeconds = 100;
    int width = 101;
    int height = 103;

    int upperLeftCount = 0;
    int upperRightCount = 0;
    int lowerLeftCount = 0;
    int lowerRightCount = 0;

    int midX = (width / 2);
    int midY = (height / 2);

    for (String line : input.split("\n")) {
      Point loc =
          new Point(
              Integer.parseInt(line.substring(2, line.indexOf(","))),
              Integer.parseInt(line.substring(line.indexOf(",") + 1, line.indexOf(" "))));

      int xv = Integer.parseInt(line.substring(line.indexOf("v=") + 2, line.lastIndexOf(",")));
      int yv = Integer.parseInt(line.substring(line.lastIndexOf(",") + 1));

      int finalX = ((loc.x + xv * totalSeconds % width) + width) % width;
      int finalY = ((loc.y + yv * totalSeconds % height) + height) % height;

      if (finalX < midX && finalY < midY) {
        upperLeftCount++;
      } else if (finalX < midX && finalY > midY) {
        lowerLeftCount++;
      } else if (finalX > midX && finalY > midY) {
        lowerRightCount++;
      } else if (finalX > midX && finalY < midY) {
        upperRightCount++;
      }
    }

    return upperRightCount * lowerLeftCount * upperLeftCount * lowerRightCount;
  }

  public long part2() {
    int width = 101;
    int height = 103;

    double lssd = Double.MAX_VALUE;

    for (int iter = 0; iter < 1_000_000; iter++) {
      List<Point> finalPoints = new ArrayList<>();

      for (String line : input.split("\n")) {
        Point loc =
            new Point(
                Integer.parseInt(line.substring(2, line.indexOf(","))),
                Integer.parseInt(line.substring(line.indexOf(",") + 1, line.indexOf(" "))));

        int xv = Integer.parseInt(line.substring(line.indexOf("v=") + 2, line.lastIndexOf(",")));
        int yv = Integer.parseInt(line.substring(line.lastIndexOf(",") + 1));

        int finalX = ((loc.x + xv * iter % width) + width) % width;
        int finalY = ((loc.y + yv * iter % height) + height) % height;
        finalPoints.add(new Point(finalX, finalY));
      }

      double meanX = (double)finalPoints.stream().mapToInt(p->p.x).sum()/finalPoints.size();
      double sumX = finalPoints.stream().mapToDouble(p->Math.pow(p.x-meanX, 2)).sum();
      double stdvX = Math.sqrt(sumX/finalPoints.size());
      double meanY = (double)finalPoints.stream().mapToInt(p->p.y).sum()/finalPoints.size();
      double sumY = finalPoints.stream().mapToDouble(p->Math.pow(p.y-meanY, 2)).sum();
      double stdvY = Math.sqrt(sumY/finalPoints.size());

      if (stdvX+stdvY < lssd && Math.abs(stdvY-stdvX)<5) {
        if(stdvX+stdvY < lssd-5 && iter>0) {
          return iter;
        }
        lssd = stdvX + stdvY;
      }
    }

    return 0;
  }


  //Not used, but had to see the pretty picture :-)
  private boolean printGrid(List<Point> points, int width, int height) {
    String pic = "";
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int fx = x;
        int fy = y;
        long r = points.stream().filter(p -> p.x == fx && p.y == fy).count();
        pic += (r == 0 ? " " : "x");
      }
      pic += "\n";
    }
    if (pic.contains("xxxxxxxxxx")) {
      System.out.println(pic);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      return true;
    }
    return false;
  }
}
