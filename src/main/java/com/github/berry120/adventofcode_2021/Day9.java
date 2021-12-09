package com.github.berry120.adventofcode_2021;

import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day9 {

  private List<List<Integer>> grid;

  public Day9(String input) {
    grid = new ArrayList<>();
    for(String line : input.split("\n")) {
      List<Integer> row = new ArrayList<>();
      for(char c : line.toCharArray()) {
        row.add(Integer.parseInt(""+c));
      }
      grid.add(row);
    }
  }

  public int part1() {
    int risk = 0;
    for(int i=0 ; i<grid.size() ; i++) {
      for(int j=0 ; j<grid.get(i).size() ; j++) {
        if(isLow(i,j)) {
          risk += 1+grid.get(i).get(j);
        }
      }
    }
    return risk;
  }

  boolean isLow(int i, int j) {
    int val = grid.get(i).get(j);
    if(i<grid.size()-1) {
      if(grid.get(i+1).get(j)<=val) {
        return false;
      }
    }
    if(i>0) {
      if(grid.get(i-1).get(j)<=val) {
        return false;
      }
    }
    if(j<grid.get(i).size()-1) {
      if(grid.get(i).get(j+1)<=val) {
        return false;
      }
    }
    if(j>0) {
      if(grid.get(i).get(j-1)<=val) {
        return false;
      }
    }
    return true;
  }

  private Index follow(int i, int j) {
    if(isLow(i,j)) {
      return new Index(i,j);
    }

    int val = grid.get(i).get(j);

    if(i<grid.size()-1) {
      if(grid.get(i+1).get(j)<val) {
        return follow(i+1,j);
      }
    }
    if(i>0) {
      if(grid.get(i-1).get(j)<val) {
        return follow(i-1,j);
      }
    }
    if(j<grid.get(i).size()-1) {
      if(grid.get(i).get(j+1)<val) {
        return follow(i,j+1);
      }
    }
    if(j>0) {
      if(grid.get(i).get(j-1)<val) {
        return follow(i,j-1);
      }
    }
    throw new RuntimeException();
  }

  public long part2() {
    Map<Index, Integer> basinMap = new HashMap<>();
    for(int i=0 ; i<grid.size() ; i++) {
      for(int j=0 ; j<grid.get(i).size() ; j++) {
        if(isLow(i,j)) {
          basinMap.put(new Index(i,j),0);
        }
      }
    }

    for(int i=0 ; i<grid.size() ; i++) {
      for(int j=0 ; j<grid.get(i).size() ; j++) {
        if(grid.get(i).get(j)==9) continue;
        Index index = follow(i,j);
        basinMap.put(index, basinMap.get(index)+1);
      }
    }

    return basinMap.values().stream().sorted((v1,v2)-> Integer.compare(v2,v1)).limit(3).reduce(1,(a,b)->a*b);
  }

  record Index(int i, int j) {}
}
