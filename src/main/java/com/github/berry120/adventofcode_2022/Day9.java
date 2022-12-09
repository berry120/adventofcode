package com.github.berry120.adventofcode_2022;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day9 {

  private final List<String> commands;

  public Day9(String input) {
    commands = new ArrayList<>();
    for (String line : input.split("\n")) {
      for (int i = 0; i < Integer.parseInt(line.split(" ")[1]); i++) {
        commands.add(line.split(" ")[0]);
      }
    }
  }

  public int part1() {
    return runCommands(2);
  }

  public int part2() {
    return runCommands(10);
  }

  public int runCommands(int numKnots) {
    List<Point> knots = new ArrayList<>();
    for (int i = 0; i < numKnots; i++) {
      knots.add(new Point(0, 0));
    }

    Set<Point> tPoints = new HashSet<>();

    for (String str : commands) {
      if (str.equals("R")) {
        knots.get(0).x++;
      }
      if (str.equals("U")) {
        knots.get(0).y++;
      }
      if (str.equals("L")) {
        knots.get(0).x--;
      }
      if (str.equals("D")) {
        knots.get(0).y--;
      }

      for (int i = 1; i < knots.size(); i++) {
        Point hpos = knots.get(i - 1);
        Point tpos = knots.get(i);

        int hDis = hpos.x - tpos.x;
        int vDis = hpos.y - tpos.y;

        if (hDis == 2 && vDis == 0) {
          tpos.x++;
        } else if (hDis == -2 && vDis == 0) {
          tpos.x--;
        } else if (hDis == 0 && vDis == 2) {
          tpos.y++;
        } else if (hDis == 0 && vDis == -2) {
          tpos.y--;
        } else if ((hDis == 1 && vDis == 2) || (hDis == 2 && vDis == 1) || (hDis == 2
            && vDis == 2)) {
          tpos.x++;
          tpos.y++;
        } else if ((hDis == -1 && vDis == 2) || (hDis == -2 && vDis == 1) || (hDis == -2
            && vDis == 2)) {
          tpos.x--;
          tpos.y++;
        } else if ((hDis == 1 && vDis == -2) || (hDis == 2 && vDis == -1) || (hDis == 2
            && vDis == -2)) {
          tpos.x++;
          tpos.y--;
        } else if ((hDis == -1 && vDis == -2) || (hDis == -2 && vDis == -1) || (hDis == -2
            && vDis == -2)) {
          tpos.x--;
          tpos.y--;
        }

      }

      tPoints.add(new Point(knots.get(knots.size() - 1).x, knots.get(knots.size() - 1).y));

    }
    return tPoints.size();
  }
}
