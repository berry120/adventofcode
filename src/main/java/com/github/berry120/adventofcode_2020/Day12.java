package com.github.berry120.adventofcode_2020;

import java.awt.Point;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day12 {

  private final String[] lines;

  public Day12(String input) {
    lines = input.split("\n");
  }

  public int part1() {
    Point ship = new Point();
    int facing = 1;

    for (String line : lines) {
      int num = Integer.parseInt(line.substring(1));
      String instr = line.substring(0,1);

      if(instr.equals("N") || instr.equals("F") && facing==0) ship = new Point(ship.x, ship.y+num);
      if(instr.equals("S") || instr.equals("F") && facing==2) ship = new Point(ship.x, ship.y-num);
      if(instr.equals("E") || instr.equals("F") && facing==1) ship = new Point(ship.x+num, ship.y);
      if(instr.equals("W") || instr.equals("F") && facing==3) ship = new Point(ship.x-num, ship.y);
      if(instr.equals("L")) facing = (facing + 3 * num / 90) % 4;
      if(instr.equals("R")) facing = (facing + num / 90) % 4;
    }

    return Math.abs(ship.x) + Math.abs(ship.y);
  }

  public int part2() {
    Point ship = new Point(0, 0);
    Point way = new Point(10, 1);

    for (String line : lines) {
      int num = Integer.parseInt(line.substring(1));
      char c = line.charAt(0);

      switch (c) {
        case 'N', 'S' -> way = new Point(way.x, way.y + (c == 'N' ? num : -num));
        case 'E', 'W' -> way = new Point(way.x + (c == 'E' ?  num : -num), way.y);
        case 'L', 'R' -> way =
            Stream.iterate(way, w -> c == 'L' ? new Point(-w.y, w.x) : new Point(w.y, -w.x))
                .limit((num / 90) + 1)
                .reduce((a, b) -> b)
                .orElseThrow();
        case 'F' -> ship = new Point(ship.x + way.x * num, ship.y + way.y * num);
      }
    }
    return Math.abs(ship.x) + Math.abs(ship.y);
  }
}
