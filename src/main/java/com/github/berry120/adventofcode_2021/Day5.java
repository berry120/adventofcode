package com.github.berry120.adventofcode_2021;

import lombok.AllArgsConstructor;
import lombok.With;
import reactor.core.publisher.Flux;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Day5 {

  private List<Line> lines;

  public Day5(String input) {
    lines = new ArrayList<>();
    for(String line : input.split("\n")) {
      String[] coords = line.split(Pattern.quote(" -> "));
      String[] coord1 = coords[0].split(",");
      String[] coord2 = coords[1].split(",");
      lines.add(new Line(Integer.parseInt(coord1[0]),Integer.parseInt(coord1[1]),Integer.parseInt(coord2[0]),Integer.parseInt(coord2[1])));
    }
  }

  public int part1() {
    List<List<Integer>> grid = new ArrayList<>();
    for(int i=0 ; i<1000 ; i++) {
      grid.add(new ArrayList<>());
      for(int j=0 ; j<1000 ; j++) {
        grid.get(i).add(0);
      }
    }
    for(Line line : lines) {
      if(line.x1==line.x2) {

        int start = Math.min(line.y1, line.y2);
        int end = Math.max(line.y1, line.y2);

        for(int i=start;i<=end;i++) {
          int val = grid.get(i).get(line.x1);
          grid.get(i).set(line.x1, val+1);
        }
      }
      else if(line.y1==line.y2) {

        int start = Math.min(line.x1, line.x2);
        int end = Math.max(line.x1, line.x2);

        for(int i=start;i<=end;i++) {
          int val = grid.get(line.y1).get(i);
          grid.get(line.y1).set(i, val+1);
        }
      }
      else {

//        System.out.println("Before:");
//
//        for(List<Integer> a : grid){
//          System.out.println(a);
//        }
//
//        System.out.println("Doing: " + line);

        int startX = Math.min(line.x1, line.x2);
        int endX = Math.max(line.x1, line.x2);
        int startY = Math.min(line.y1, line.y2);
        int endY = Math.max(line.y1, line.y2);
        int len=endX-startX;

        int yPos;
        if(line.y2==startY) {
          yPos=startY;
        }
        else {
          yPos = endY;
        }

        int xPos;
        if(line.x2==startX) {
          xPos=startX;
        }
        else {
          xPos = endX;
        }

        for(int i=0 ; i<=len ; i++) {
          int val = grid.get(yPos).get(xPos);
          grid.get(yPos).set(xPos, val+1);
          if(line.y2==startY) {
            yPos++;
          }
          else {
            yPos--;
          }
          if(line.x2==startX) {
            xPos++;
          }
          else {
            xPos--;
          }
        }

//        for(List<Integer> a : grid){
//          System.out.println(a);
//        }

      }
    }

//    System.out.println("FINAL");

//    for(List<Integer> line : grid){
//      System.out.println(line);
//    }

    return (int)grid.stream()
            .flatMap(x->x.stream().filter(y->y>1))
            .count();
  }

  public int part2() {
    return 0;
  }

  record Line(int x1,int y1,int x2, int y2) {


  }

}
