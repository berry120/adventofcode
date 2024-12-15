package com.github.berry120.adventofcode_2024;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day15 {
  private String input;

  public int part1() {
    String[] parts = input.split("\n\n");
    String gridStr = parts[0];
    String instructions = parts[1];

    Point pos = null;
    Set<Point> walls = new HashSet<>();
    Set<Point> boxes = new HashSet<>();
    Set<Point> gaps = new HashSet<>();

    {
      int y = 0;
      for (String line : gridStr.split("\n")) {
        int x = 0;
        for (char c : line.toCharArray()) {
          if (c == '#') {
            walls.add(new Point(x, y));
          } else if (c == '@') {
            pos = new Point(x, y);
          } else if (c == 'O') {
            boxes.add(new Point(x, y));
          } else if (c == '.') {
            gaps.add(new Point(x, y));
          }
          x++;
        }
        y++;
      }
    }


    for(char c: instructions.toCharArray()) {

      if(c=='<') {
        boolean endInGap;
        List<Point> affectedBoxes = new ArrayList<>();
        for(int x = pos.x-1 ; ; x--) {
          if(walls.contains(new Point(x, pos.y))) {
            endInGap = false;
            break;
          }
          if(gaps.contains(new Point(x, pos.y))) {
            endInGap = true;
            break;
          }
          affectedBoxes.add(new Point(x, pos.y));
        }

        if(endInGap) {
          Collections.reverse(affectedBoxes);
          for(Point boxPoint : affectedBoxes) {
            boxes.remove(boxPoint);
            Point newPoint = new Point(boxPoint.x-1, boxPoint.y);
            gaps.remove(newPoint);
            boxes.add(newPoint);
          }
          gaps.add(pos);
          pos = new Point(pos.x-1, pos.y);
          gaps.remove(pos);
        }
        else {
          continue;
        }

      }
      if(c=='>') {

        boolean endInGap;
        List<Point> affectedBoxes = new ArrayList<>();
        for(int x = pos.x+1 ; ; x++) {
          if(walls.contains(new Point(x, pos.y))) {
            endInGap = false;
            break;
          }
          if(gaps.contains(new Point(x, pos.y))) {
            endInGap = true;
            break;
          }
          affectedBoxes.add(new Point(x, pos.y));
        }

        if(endInGap) {
          Collections.reverse(affectedBoxes);
          for(Point boxPoint : affectedBoxes) {
            boxes.remove(boxPoint);
            Point newPoint = new Point(boxPoint.x+1, boxPoint.y);
            gaps.remove(newPoint);
            boxes.add(newPoint);
          }
          gaps.add(pos);
          pos = new Point(pos.x+1, pos.y);
          gaps.remove(pos);
        }
        else {
          continue;
        }


      }
      if(c=='^') {
        boolean endInGap;
        List<Point> affectedBoxes = new ArrayList<>();
        for(int y = pos.y-1 ; ; y--) {
          if(walls.contains(new Point(pos.x, y))) {
            endInGap = false;
            break;
          }
          if(gaps.contains(new Point(pos.x, y))) {
            endInGap = true;
            break;
          }
          affectedBoxes.add(new Point(pos.x, y));
        }

        if(endInGap) {
          Collections.reverse(affectedBoxes);
          for(Point boxPoint : affectedBoxes) {
            boxes.remove(boxPoint);
            Point newPoint = new Point(boxPoint.x, boxPoint.y-1);
            gaps.remove(newPoint);
            boxes.add(newPoint);
          }
          gaps.add(pos);
          pos = new Point(pos.x, pos.y-1);
          gaps.remove(pos);
        }
        else {
          continue;
        }

      }
      if(c=='v') {

        boolean endInGap;
        List<Point> affectedBoxes = new ArrayList<>();
        for(int y = pos.y+1 ; ; y++) {
          if(walls.contains(new Point(pos.x, y))) {
            endInGap = false;
            break;
          }
          if(gaps.contains(new Point(pos.x, y))) {
            endInGap = true;
            break;
          }
          affectedBoxes.add(new Point(pos.x, y));
        }

        if(endInGap) {
          Collections.reverse(affectedBoxes);
          for(Point boxPoint : affectedBoxes) {
            boxes.remove(boxPoint);
            Point newPoint = new Point(boxPoint.x, boxPoint.y+1);
            gaps.remove(newPoint);
            boxes.add(newPoint);
          }
          gaps.add(pos);
          pos = new Point(pos.x, pos.y+1);
          gaps.remove(pos);
        }
        else {
          continue;
        }

      }
    }

    return boxes.stream().mapToInt(b->b.x+b.y*100).sum();
  }

  record Box(Point a, Point b) {}

  public long part2() {
    String[] parts = input.split("\n\n");
    String gridStr = parts[0];
    String instructions = parts[1];

    Point pos = null;
    Set<Point> walls = new HashSet<>();
    Set<Box> boxes = new HashSet<>();
    Set<Point> gaps = new HashSet<>();

    {
      int y = 0;
      for (String line : gridStr.split("\n")) {
        line = line.replace("#", "##").replace("O", "[]").replace(".", "..").replace("@", "@.");
        int x = 0;
        for (char c : line.toCharArray()) {
          if (c == '#') {
            walls.add(new Point(x, y));
          } else if (c == '@') {
            pos = new Point(x, y);
          } else if (c == '[') {
            boxes.add(new Box(new Point(x, y),new Point(x+1, y)));
          } else if (c == '.') {
            gaps.add(new Point(x, y));
          } else if(c==']') {

          }
          else {
            throw new RuntimeException();
          }
          x++;
        }
        y++;
      }
    }

    for(char c: instructions.toCharArray()) {

      if(c=='<') {
        boolean endInGap;
        List<Box> affectedBoxes = new ArrayList<>();
        for(int x = pos.x-1 ; ; x--) {
          if(walls.contains(new Point(x, pos.y))) {
            endInGap = false;
            break;
          }
          if(gaps.contains(new Point(x, pos.y))) {
            endInGap = true;
            break;
          }
          final Point fpos = pos;
          final int fx = x;
          Box box = boxes.stream().filter(b->b.a.equals(new Point(fx, fpos.y))||b.b.equals(new Point(fx, fpos.y))).findAny().get();
          if(!affectedBoxes.contains(box)) {
            affectedBoxes.add(box);
          }
        }

        if(endInGap) {
          Collections.reverse(affectedBoxes);
          for(Box box : affectedBoxes) {
            boxes.remove(box);
            gaps.add(box.a);
            gaps.add(box.b);
            Box newBox = new Box(new Point(box.a.x-1, box.a.y), new Point(box.b.x-1, box.b.y));
            gaps.remove(newBox.a);
            gaps.remove(newBox.b);
            boxes.add(newBox);
          }
          gaps.add(pos);
          pos = new Point(pos.x-1, pos.y);
          gaps.remove(pos);
        }
        else {
          continue;
        }

      }
      if(c=='>') {

        boolean endInGap;
        List<Box> affectedBoxes = new ArrayList<>();
        for(int x = pos.x+1 ; ; x++) {
          if(walls.contains(new Point(x, pos.y))) {
            endInGap = false;
            break;
          }
          if(gaps.contains(new Point(x, pos.y))) {
            endInGap = true;
            break;
          }
          final Point fpos = pos;
          final int fx = x;
          Box box = boxes.stream().filter(b->b.a.equals(new Point(fx, fpos.y))||b.b.equals(new Point(fx, fpos.y))).findAny().get();
          if(!affectedBoxes.contains(box)) {
            affectedBoxes.add(box);
          }
        }

        if(endInGap) {
          Collections.reverse(affectedBoxes);
          for(Box box : affectedBoxes) {
            boxes.remove(box);
            gaps.add(box.a);
            gaps.add(box.b);
            Box newBox = new Box(new Point(box.a.x+1, box.a.y), new Point(box.b.x+1, box.b.y));
            gaps.remove(newBox.a);
            gaps.remove(newBox.b);
            boxes.add(newBox);
          }
          gaps.add(pos);
          pos = new Point(pos.x+1, pos.y);
          gaps.remove(pos);
        }
        else {
          continue;
        }


      }
      if(c=='^') {

        Point potentialPoint = new Point(pos.x, pos.y-1);
        if(gaps.contains(potentialPoint)) {
          gaps.remove(potentialPoint);
          gaps.add(pos);
          pos = potentialPoint;
        }
        else if(walls.contains(potentialPoint)) {
          continue;
        }
        else {
          Box directBox = boxes.stream().filter(b->b.a.equals(potentialPoint)||b.b.equals(potentialPoint)).findAny().get();

          Set<Box> affectedBoxes = new HashSet<>();
          affectedBoxes.add(directBox);
          affectedBoxes.addAll(getAbove(boxes, directBox.a));
          affectedBoxes.addAll(getAbove(boxes, directBox.b));

          List<Point> toCheckBlocked = affectedBoxes.stream().flatMap(b->Stream.of(b.a,b.b)).map(p->new Point(p.x,p.y-1)).toList();

          boolean blocked = false;
          for(Point p : toCheckBlocked) {
            if(walls.contains(p)) {
              blocked = true;
            }
          }
          if(blocked) continue;

          int minY = affectedBoxes.stream().mapToInt(b->b.a.y).min().getAsInt();
          List<Box> topBoxes = affectedBoxes.stream().filter(b->b.a.y==minY).toList();
          List<Point> toCheckEmpty =
              topBoxes.stream().flatMap(b -> Stream.of(b.a, b.b)).map(p -> new Point(p.x,p.y-1)).toList();
          if(gaps.containsAll(toCheckEmpty)) {
            toCheckEmpty.forEach(gaps::remove);
            gaps.addAll(affectedBoxes.stream().flatMap(b->Stream.of(b.a,b.b)).toList());
            boxes.removeAll(affectedBoxes);
            boxes.addAll(affectedBoxes.stream().map(b->new Box(new Point(b.a.x,b.a.y-1), new Point(b.b.x,b.b.y-1))).toList());
            boxes.stream().flatMap(b->Stream.of(b.a,b.b)).toList().forEach(gaps::remove);
            gaps.add(pos);
            gaps.remove(potentialPoint);
            pos = potentialPoint;

          }
          else {
            continue;
          }
        }



      }
      if(c=='v') {


        Point potentialPoint = new Point(pos.x, pos.y+1);
        if(gaps.contains(potentialPoint)) {
          gaps.remove(potentialPoint);
          gaps.add(pos);
          pos = potentialPoint;
        }
        else if(walls.contains(potentialPoint)) {
          continue;
        }
        else {

          Box directBox = boxes.stream().filter(b->b.a.equals(potentialPoint)||b.b.equals(potentialPoint)).findAny().get();

          Set<Box> affectedBoxes = new HashSet<>();
          affectedBoxes.add(directBox);
          affectedBoxes.addAll(getBelow(boxes, directBox.a));
          affectedBoxes.addAll(getBelow(boxes, directBox.b));

          List<Point> toCheckBlocked = affectedBoxes.stream().flatMap(b->Stream.of(b.a,b.b)).map(p->new Point(p.x,p.y+1)).toList();
          boolean blocked = false;
          for(Point p : toCheckBlocked) {
            if(walls.contains(p)) {
              blocked = true;
            }
          }
          if(blocked) continue;

          int maxY = affectedBoxes.stream().mapToInt(b->b.a.y).max().getAsInt();
          List<Box> bottomBoxes = affectedBoxes.stream().filter(b->b.a.y==maxY).toList();
          List<Point> toCheckEmpty =
              bottomBoxes.stream().flatMap(b -> Stream.of(b.a, b.b)).map(p -> new Point(p.x,p.y+1)).toList();
          if(gaps.containsAll(toCheckEmpty)) {

            for(var v : toCheckEmpty) {
              if(walls.contains(v)) throw new RuntimeException("Ouch?");
            }

            toCheckEmpty.forEach(gaps::remove);
            gaps.addAll(affectedBoxes.stream().flatMap(b->Stream.of(b.a,b.b)).toList());
            boxes.removeAll(affectedBoxes);
            boxes.addAll(affectedBoxes.stream().map(b->new Box(new Point(b.a.x,b.a.y+1), new Point(b.b.x,b.b.y+1))).toList());
            boxes.stream().flatMap(b->Stream.of(b.a,b.b)).toList().forEach(gaps::remove);
            gaps.add(pos);
            gaps.remove(potentialPoint);
            pos = potentialPoint;

          }
          else {
            continue;
          }

        }
      }
    }
    return boxes.stream().mapToInt(b->b.a.x+b.a.y*100).sum();
  }

  private Set<Box> getAbove(Set<Box> boxes, Point p) {
    Set<Box> ret= new HashSet<>();
    Point newPoint = new Point(p.x, p.y-1);

    Box above = boxes.stream().filter(b->b.a.equals(newPoint)||b.b.equals(newPoint)).findAny().orElse(null);
    if(above==null) {
      return ret;
    }
    else {
      ret.add(above);
      ret.addAll(getAbove(boxes, above.a));
      ret.addAll(getAbove(boxes, above.b));
    }
    return ret;
  }

  private Set<Box> getBelow(Set<Box> boxes, Point p) {
    Set<Box> ret= new HashSet<>();
    Point newPoint = new Point(p.x, p.y+1);

    Box above = boxes.stream().filter(b->b.a.equals(newPoint)||b.b.equals(newPoint)).findAny().orElse(null);
    if(above==null) {
      return ret;
    }
    else {
      ret.add(above);
      ret.addAll(getBelow(boxes, above.a));
      ret.addAll(getBelow(boxes, above.b));
    }
    return ret;
  }

}
