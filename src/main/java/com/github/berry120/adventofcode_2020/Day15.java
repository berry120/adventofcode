package com.github.berry120.adventofcode_2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day15 {

  private final List<Integer> nums;

  public Day15(String input) {
    nums = Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList());
  }

  public int part1() {
    return getLastNum(2020);
  }

  public long part2() {
    return getLastNum(30000000);
  }

  public int getLastNum(int iterations) {
    Map<Integer, List<Integer>> numPlaces = new HashMap<>();
    int lastNum = nums.get(nums.size() - 1);
    int idx = nums.size();

    for (int i = 0; i < nums.size(); i++) {
      int num = nums.get(i);
      List<Integer> list = numPlaces.getOrDefault(num, new ArrayList<>());
      list.add(i);
      numPlaces.put(num, list);
    }

    while (idx < iterations) {
      List<Integer> idxs = numPlaces.get(lastNum);

      int toAdd;
      if (idxs.size() == 1) {
        toAdd = 0;
      } else {
        toAdd = (idx - 1) - idxs.get(idxs.size() - 2);
      }

      List<Integer> idxs2 = numPlaces.getOrDefault(toAdd, new ArrayList<>());
      idxs2.add(idx);
      numPlaces.put(toAdd, idxs2);

      lastNum = toAdd;
      idx++;

    }

    return lastNum;
  }

}
