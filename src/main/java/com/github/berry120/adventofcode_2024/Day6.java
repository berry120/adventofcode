package com.github.berry120.adventofcode_2024;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day6 {

  enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
  }

  record Carrier(Point p, Direction d) {}

  private String input;

  public int part1() {
    boolean[][] map = new boolean[input.split("\n")[0].length()][input.split("\n").length];

    Point loc = null;
    Direction direction = Direction.UP;

    Set<Point> visited = new HashSet<>();

    int y = 0;
    for (String str : input.split("\n")) {
      int x = 0;
      for (char c : str.toCharArray()) {
        map[x][y] = c == '#';
        if (c == '^') {
          loc = new Point(x, y);
        }
        x++;
      }
      y++;
    }

    while (true) {
      visited.add(loc);
      Point newLocation;
      if (direction == Direction.UP) {
        newLocation = new Point(loc.x, loc.y - 1);
      } else if (direction == Direction.DOWN) {
        newLocation = new Point(loc.x, loc.y + 1);
      } else if (direction == Direction.LEFT) {
        newLocation = new Point(loc.x - 1, loc.y);
      } else if (direction == Direction.RIGHT) {
        newLocation = new Point(loc.x + 1, loc.y);
      } else {
        throw new RuntimeException();
      }

      if (newLocation.x < 0
          || newLocation.y < 0
          || newLocation.x >= input.split("\n")[0].length()
          || newLocation.y >= input.split("\n").length) {
        break;
      } else if (map[newLocation.x][newLocation.y]) {
        if (direction == Direction.UP) {
          direction = Direction.RIGHT;
        } else if (direction == Direction.DOWN) {
          direction = Direction.LEFT;
        } else if (direction == Direction.LEFT) {
          direction = Direction.UP;
        } else if (direction == Direction.RIGHT) {
          direction = Direction.DOWN;
        } else {
          throw new RuntimeException();
        }
      } else {
        loc = newLocation;
      }
    }

    return visited.size();
  }

  public int part2() {
    boolean[][] map = new boolean[input.split("\n")[0].length()][input.split("\n").length];

    Point startingLoc = null;
    Point loc = null;
    Direction direction = Direction.UP;

    {
      int y = 0;
      for (String str : input.split("\n")) {
        int x = 0;
        for (char c : str.toCharArray()) {
          map[x][y] = c == '#';
          if (c == '^') {
            startingLoc = new Point(x, y);
            loc = new Point(x, y);
          }
          x++;
        }
        y++;
      }
    }

    List<Point> standardVisitedPoints = new ArrayList<>();
    while (true) {
      standardVisitedPoints.add(loc);
      Point newLocation;
      if (direction == Direction.UP) {
        newLocation = new Point(loc.x, loc.y - 1);
      } else if (direction == Direction.DOWN) {
        newLocation = new Point(loc.x, loc.y + 1);
      } else if (direction == Direction.LEFT) {
        newLocation = new Point(loc.x - 1, loc.y);
      } else if (direction == Direction.RIGHT) {
        newLocation = new Point(loc.x + 1, loc.y);
      } else {
        throw new RuntimeException();
      }

      if (newLocation.x < 0
          || newLocation.y < 0
          || newLocation.x >= input.split("\n")[0].length()
          || newLocation.y >= input.split("\n").length) {
        break;
      } else if (map[newLocation.x][newLocation.y]) {
        if (direction == Direction.UP) {
          direction = Direction.RIGHT;
        } else if (direction == Direction.DOWN) {
          direction = Direction.LEFT;
        } else if (direction == Direction.LEFT) {
          direction = Direction.UP;
        } else if (direction == Direction.RIGHT) {
          direction = Direction.DOWN;
        } else {
          throw new RuntimeException();
        }
      } else {
        loc = newLocation;
      }
    }



    int numLooped = 0;

    System.out.println(standardVisitedPoints.size());
    int i = 0;
    for (Point standardPoint : standardVisitedPoints) {
      System.out.println(i++);

      int x = standardPoint.x;
      int y = standardPoint.y;

      boolean oldVal = map[x][y];
      if (oldVal == false) {
        map[x][y] = true;
      }
      loc = startingLoc;
      direction = Direction.UP;

      List<Carrier> path = new ArrayList<>();
      Set<Carrier> visited = new HashSet<>();
      while (true) {
        if (!visited.add(new Carrier(loc, direction))) {
          numLooped++;
          break;
        }
        Point newLocation;
        if (direction == Direction.UP) {
          newLocation = new Point(loc.x, loc.y - 1);
        } else if (direction == Direction.DOWN) {
          newLocation = new Point(loc.x, loc.y + 1);
        } else if (direction == Direction.LEFT) {
          newLocation = new Point(loc.x - 1, loc.y);
        } else if (direction == Direction.RIGHT) {
          newLocation = new Point(loc.x + 1, loc.y);
        } else {
          throw new RuntimeException();
        }

        if (newLocation.x < 0
            || newLocation.y < 0
            || newLocation.x >= input.split("\n")[0].length()
            || newLocation.y >= input.split("\n").length) {
          break;
        } else if (map[newLocation.x][newLocation.y]) {
          if (direction == Direction.UP) {
            direction = Direction.RIGHT;
          } else if (direction == Direction.DOWN) {
            direction = Direction.LEFT;
          } else if (direction == Direction.LEFT) {
            direction = Direction.UP;
          } else if (direction == Direction.RIGHT) {
            direction = Direction.DOWN;
          } else {
            throw new RuntimeException();
          }
        } else {
          loc = newLocation;
        }
      }
      map[x][y] = oldVal;
    }
    return numLooped;
  }
}
