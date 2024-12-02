package com.github.berry120.adventofcode_2024;


import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class Day1 {

  private String input;

  public int part1() {
    String[] lines = input.split("\n");
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    for(String line : lines) {
      String[] parts = line.split("   ");
      list1.add(Integer.parseInt(parts[0]));
      list2.add(Integer.parseInt(parts[1]));
    }
    Collections.sort(list1);
    Collections.sort(list2);

    int ret = 0;
    for(int i=0 ; i<list1.size() ; i++) {
      ret+= Math.abs(list1.get(i)-list2.get(i));
    }
    return ret;
  }

  public int part2() {
    String[] lines = input.split("\n");
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    for(String line : lines) {
      String[] parts = line.split("   ");
      list1.add(Integer.parseInt(parts[0]));
      list2.add(Integer.parseInt(parts[1]));
    }
    Collections.sort(list1);

    int ret = 0;
    for(int i=0 ; i<list1.size() ; i++) {
      ret+= list1.get(i)*Collections.frequency(list2, list1.get(i));
    }
    return ret;
  }
}
