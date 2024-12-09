package com.github.berry120.adventofcode_2024;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day9 {

  private String input;

  public long part1() {
    List<Integer> fileMap = new ArrayList<>();
    int id = 0;
    boolean file = true;

    for (char c : input.toCharArray()) {
      int length = Character.getNumericValue(c);
      if (file) {
        for (int i = 0; i < length; i++) {
          fileMap.add(id);
        }
        id++;
      } else {
        for (int i = 0; i < length; i++) {
          fileMap.add(-1);
        }
      }
      file = !file;
    }

    while (fileMap.contains(-1)) {
      int lastChar = fileMap.get(fileMap.size() - 1);
      if (lastChar == -1) {
        fileMap.removeLast();
      } else {
        fileMap.set(fileMap.indexOf(-1), lastChar);
        fileMap.removeLast();
      }
    }

    long checksum = 0;
    for (int i = 0; i < fileMap.size(); i++) {
      int c = fileMap.get(i);
      checksum += ((long) c * i);
    }

    return checksum;
  }

  public long part2() {
    List<Integer> fileMap = new ArrayList<>();
    int id = 0;
    boolean file = true;

    for (char c : input.toCharArray()) {
      int length = Character.getNumericValue(c);
      if (file) {
        for (int i = 0; i < length; i++) {
          fileMap.add(id);
        }
        id++;
      } else {
        for (int i = 0; i < length; i++) {
          fileMap.add(-1);
        }
      }
      file = !file;
    }

    int maxId = fileMap.stream().mapToInt(i -> i).max().getAsInt();

    for (int i = maxId; i >= 0; i--) {
      System.out.println(i);
      final int fi = i;
      long idLength = fileMap.stream().filter(e -> e == fi).count();

      int firstDotPos = -1;
      for (int j = 0; j < fileMap.size(); j++) {
        if(j>fileMap.indexOf(fi)) {
          break;
        }
        int n = fileMap.get(j);
        if (n != -1) {
          firstDotPos = -1;
          continue;
        }
        if (firstDotPos == -1) {
          firstDotPos = j;
        }
        if (j - firstDotPos >= idLength-1) {
          fileMap.replaceAll(e -> e == fi ? -1 : e);
          for (int k = 0; k < idLength; k++) {
            fileMap.set(firstDotPos + k, fi);
          }
          break;
        }
      }
    }

    long checksum = 0;
    for (int i = 0; i < fileMap.size(); i++) {
      int c = fileMap.get(i);
      if (c == -1) continue;
      checksum += ((long) c * i);
    }

    return checksum;
  }
}
