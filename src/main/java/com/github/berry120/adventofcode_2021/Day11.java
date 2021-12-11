package com.github.berry120.adventofcode_2021;

import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Day11 {

  private final List<List<Octopus>> grid;

  public Day11(String input) {
    grid = new ArrayList<>();
    for (String line : input.split("\n")) {
      List<Octopus> a = new ArrayList<>();
      for (char c : line.toCharArray()) {
        a.add(new Octopus(Integer.parseInt("" + c)));
      }
      grid.add(a);
    }
  }

  public int part1() {
    int tF = 0;
    for (int i = 0; i < 500; i++) {
      int ftt = 0;
      // Increase 1
      for (int a = 0; a < grid.size(); a++) {
        List<Octopus> x = grid.get(a);
        for (int b = 0; b < x.size(); b++) {
          x.get(b).increase();
        }
      }

      while (true) {
        int flashes = 0;
        for (int a = 0; a < grid.size(); a++) {
          List<Octopus> x = grid.get(a);
          for (int b = 0; b < x.size(); b++) {
            Octopus o = x.get(b);
            if (!o.flashed && o.num > 9) {
              increaseAdj(a, b);
              o.flashed = true;
              o.num = 0;
              flashes++;
              tF++;
              ftt++;
            }
          }
        }
        if (flashes == 0) break;
      }

      // Reset
      for (int a = 0; a < grid.size(); a++) {
        List<Octopus> x = grid.get(a);
        for (int b = 0; b < x.size(); b++) {
          x.get(b).resetIfFlashed();
        }
      }
      if (ftt == 100) {
        System.out.println("Part 2: " + (i + 1));
        break;
      }
    }
    return tF;
  }

  void print() {
    for (int i = 0; i < grid.size(); i++) {
      for (int j = 0; j < grid.get(i).size(); j++) {
        System.out.print(grid.get(i).get(j).num);
        if (grid.get(i).get(j).num < 10) {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
    System.out.println();
    System.out.println("---");
    System.out.println();
  }

  void increaseAdj(int a, int b) {
    //    System.out.println("Increasing " + a + "," + b);
    //    print();
    increase(a - 1, b - 1);
    increase(a - 1, b);
    increase(a - 1, b + 1);
    increase(a, b - 1);
    increase(a, b + 1);
    increase(a + 1, b - 1);
    increase(a + 1, b);
    increase(a + 1, b + 1);
    //    print();
  }

  void increase(int a, int b) {
    try {
      grid.get(a).get(b).increase();
    } catch (Exception ex) {
    }
  }

  public int part2() {
    return 0;
  }

  static class Octopus {

    int num;
    boolean flashed;

    public Octopus(int num) {
      this.num = num;
    }

    void increase() {
      num++;
    }

    void resetIfFlashed() {
      if (flashed) {
        flashed = false;
        num = 0;
      }
    }
  }
}
