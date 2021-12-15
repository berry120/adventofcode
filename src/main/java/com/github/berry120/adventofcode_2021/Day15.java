package com.github.berry120.adventofcode_2021;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Day15 {

  private final List<List<Node>> grid;

  public Day15(String str) {
    grid = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      for (String line : str.split("\n")) {
        List<Node> gridLine = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
          for (char c : line.toCharArray()) {
            int val = Integer.parseInt("" + c) + i + j;
            while (val > 9) {
              val -= 9;
            }
            gridLine.add(new Node(val, Integer.MAX_VALUE, false));
          }
        }
        grid.add(gridLine);
      }
    }
    grid.get(0).get(0).setDistance(0);
  }

  List<Node> getNeighbours(int x, int y) {
    List<Node> ret = new ArrayList<>();
    try {
      ret.add(grid.get(y).get(x + 1));
    } catch (Exception ex) {
    }
    try {
      ret.add(grid.get(y).get(x - 1));
    } catch (Exception ex) {
    }
    try {
      ret.add(grid.get(y + 1).get(x));
    } catch (Exception ex) {
    }
    try {
      ret.add(grid.get(y - 1).get(x));
    } catch (Exception ex) {
    }
    return ret;
  }

  public int part1() {
    int curX = 0;
    int curY = 0;
    int progress = 0;
    int total = grid.size() * grid.get(0).size();
    while (true) {
      Node cur = grid.get(curY).get(curX);
      if(cur.isVisited()) {
        System.out.println(curX + "," + curY);
        System.exit(2);
      }
      for (Node neighbour : getNeighbours(curX, curY)) {
        int newDist = cur.getDistance() + neighbour.getVal();
        if (neighbour.getDistance() > newDist) {
          neighbour.setDistance(newDist);
        }
      }

      cur.setVisited(true);
      progress++;
      if (progress % 1000 == 0) System.out.println(progress + "/" + total);

      int length = grid.get(0).size();
      int height = grid.size();

      int smallX = 0;
      int smallY = 0;
      int smallDistance = Integer.MAX_VALUE;

      for (int y = 0; y < height; y++) {
        for (int x = 0; x < length; x++) {
          Node n = grid.get(y).get(x);
          if (!n.isVisited()) {
            if (n.getDistance() < smallDistance) {
              smallDistance = n.getDistance();
              smallX = x;
              smallY = y;
            }
          }
        }
      }
      if (smallDistance == Integer.MAX_VALUE) {
        break;
      }
      curX = smallX;
      curY = smallY;
    }

    List<Node> lastLine = grid.get(grid.size() - 1);
    return lastLine.get(lastLine.size() - 1).getDistance();
  }

  public int part2() {
    return 0;
  }

  @Data
  @AllArgsConstructor
  static class Node {
    int val;
    int distance;
    boolean visited;
  }
}
