package com.github.berry120.adventofcode_2024;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day4 {

  private String input;

  public long part1() {
    List<String> searchList = new ArrayList<>();
    String[] lines = input.split("\n");
    int lineLength = input.split("\n")[0].length();
    int numLines = input.split("\n").length;

    searchList.addAll(input.lines().toList());

    List<String> reversed =
        new ArrayList<>(input.lines().map(s -> new StringBuilder(s).reverse().toString()).toList());
    searchList.addAll(reversed);

    for (int x = 0; x < lineLength; x++) {
      String down = "";
      for (int y = 0; y < numLines; y++) {
        down += lines[y].charAt(x);
      }
      searchList.add(down);
      searchList.add(new StringBuilder(down).reverse().toString());
    }

    for (int yStart = 1; yStart < numLines; yStart++) {
      String diag = "";
      for (int y = yStart, x = 0; y < numLines & x < lineLength; y++, x++) {
        diag += lines[y].charAt(x);
      }
      searchList.add(diag);
      searchList.add(new StringBuilder(diag).reverse().toString());
    }
    for (int xStart = 0; xStart < numLines; xStart++) {
      String diag = "";
      for (int y = 0, x = xStart; y < numLines & x < lineLength; y++, x++) {
        diag += lines[y].charAt(x);
      }
      searchList.add(diag);
      searchList.add(new StringBuilder(diag).reverse().toString());
    }

    for (int yStart = 1; yStart < numLines; yStart++) {
      String diag = "";
      for (int y = yStart, x = lineLength - 1; y < numLines && x >= 0; y++, x--) {
        diag += lines[y].charAt(x);
      }
      searchList.add(diag);
      searchList.add(new StringBuilder(diag).reverse().toString());
    }
    for (int xStart = 0; xStart < numLines; xStart++) {
      String diag = "";
      for (int y = 0, x = xStart; y < numLines && x >= 0; y++, x--) {
        diag += lines[y].charAt(x);
      }
      searchList.add(diag);
      searchList.add(new StringBuilder(diag).reverse().toString());
    }

    int num = 0;
    for (String line : searchList) {
      for (int i = 0; i < line.length(); i++) {
        if (line.substring(i).startsWith("XMAS")) num++;
      }
    }
    return num;
  }

  public long part2() {
    String[] lines = input.split("\n");

    return IntStream.range(0, lines[0].length() - 2)
        .boxed()
        .flatMap(x -> IntStream.range(0, lines.length - 2).mapToObj(y -> new int[] {x, y}))
        .filter(
            arr ->
                ((lines[arr[0] + 1].charAt(arr[1] + 1) == 'A'
                        && ((lines[arr[0] + 2].charAt(arr[1] + 2) == 'M'
                                && lines[arr[0]].charAt(arr[1]) == 'S')
                            || (lines[arr[0] + 2].charAt(arr[1] + 2) == 'S'
                                && lines[arr[0]].charAt(arr[1]) == 'M')))
                    && ((lines[arr[0] + 2].charAt(arr[1]) == 'M'
                            && lines[arr[0]].charAt(arr[1] + 2) == 'S')
                        || lines[arr[0] + 2].charAt(arr[1]) == 'S'
                            && lines[arr[0]].charAt(arr[1] + 2) == 'M')))
        .count();
  }
}
