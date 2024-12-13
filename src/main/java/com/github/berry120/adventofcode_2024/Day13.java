package com.github.berry120.adventofcode_2024;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day13 {
  private String input;

  public long part1() {
    return calculateCost(0);
  }

  public long part2() {
    return calculateCost(10000000000000L);
  }

  public long calculateCost(long resultAdd) {
    String[] parts = input.split("\n\n");
    long cost = 0;
    for(String equation : parts) {
      String[] lines = equation.split("\n");
      long ax = Long.parseLong(lines[0].substring(lines[0].indexOf("X+")+2, lines[0].indexOf(",")));
      long ay = Long.parseLong(lines[0].substring(lines[0].indexOf("Y+")+2));
      long bx = Long.parseLong(lines[1].substring(lines[1].indexOf("X+")+2, lines[1].indexOf(",")));
      long by = Long.parseLong(lines[1].substring(lines[1].indexOf("Y+")+2));
      long x = Long.parseLong(lines[2].substring(lines[2].indexOf("X=")+2, lines[2].indexOf(","))) + resultAdd;
      long y = Long.parseLong(lines[2].substring(lines[2].indexOf("Y=")+2)) + resultAdd;

      double a = ((double)(bx*y)-(by*x))/((bx*ay-by*ax));
      double b = ((double)y-(ay*a))/by;

      if(a%1==0&&b%1==0) {
        cost += a*3+b;
      }
    }
    return cost;
  }
}
