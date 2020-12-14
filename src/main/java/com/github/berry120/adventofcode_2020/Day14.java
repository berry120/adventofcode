package com.github.berry120.adventofcode_2020;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day14 {

  public static final String PAD_0 = "000000000000000000000000000000000000";

  private final String[] lines;

  public Day14(String input) {
    lines = input.split("\n");
  }

  public long part1() {
    Map<String, Long> mem = new HashMap<>();
    String mask = null;

    for (String line : lines) {
      if (line.startsWith("mask")) {
        mask = line.substring(7);
      } else {
        String address = line.substring(4, line.indexOf(']'));

        String num = Long.toBinaryString(Long.parseLong(line.substring(line.indexOf('=') + 2)));
        char[] paddedNum = (PAD_0 + num).substring(num.length()).toCharArray();

        for (int i = 0; i < mask.length(); i++) {
          if (mask.charAt(i) == '1' || mask.charAt(i) == '0') {
            paddedNum[i] = mask.charAt(i);
          }
        }

        mem.put(address, Long.parseLong(new String(paddedNum), 2));
      }
    }

    return mem.values().stream().reduce(Long::sum).orElseThrow();
  }

  public long part2() {
    Map<Long, Long> mem = new HashMap<>();
    String mask = null;

    for (String line : lines) {
      if (line.startsWith("mask")) {
        mask = line.substring(7);
      } else {
        String address = Long.toBinaryString(Long.parseLong(line.substring(4, line.indexOf(']'))));
        char[] paddedAddress = (PAD_0 + address).substring(address.length()).toCharArray();

        for (int i = 0; i < mask.length(); i++) {
          if (mask.charAt(i) == '1' || mask.charAt(i) == 'X') {
            paddedAddress[i] = mask.charAt(i);
          }
        }

        long val = Long.parseLong(line.substring(line.indexOf('=') + 2));

        mem.putAll(resolveAddresses(new String(paddedAddress)).map(v -> Long.parseLong(v, 2))
            .collect(Collectors.toMap(l -> l, l -> val)));
      }
    }

    return mem.values().stream().reduce(Long::sum).orElseThrow();
  }

  private Stream<String> resolveAddresses(String num) {
    return num.contains("X") ?
        Stream.concat(
            resolveAddresses(
                num.substring(0, num.indexOf('X')) + '0' + num.substring(num.indexOf('X') + 1)),
            resolveAddresses(
                num.substring(0, num.indexOf('X')) + '1' + num.substring(num.indexOf('X') + 1))
        )
        : Stream.of(num);
  }

}
