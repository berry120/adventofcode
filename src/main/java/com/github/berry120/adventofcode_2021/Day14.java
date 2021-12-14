package com.github.berry120.adventofcode_2021;

import lombok.AllArgsConstructor;
import java.math.BigInteger;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class Day14 {

  private final String start;
  private final Map<String, String> rules;

  public Day14(String input) {
    start = input.split("\n")[0].trim();
    rules = new HashMap<>();
    for (String line : input.split("\n\n")[1].trim().split("\n")) {
      rules.put(line.split(" -> ")[0], line.split(" -> ")[1]);
    }
  }

  <T> Map<T, Long> frequencyMap(Stream<T> elements) {
    return elements.collect(
        Collectors.groupingBy(
            Function.identity(),
            HashMap::new, // can be skipped
            Collectors.counting()));
  }

  public int part1() {
    String s = start;
    for (int r = 0; r < 10; r++) {
      System.out.println(r);
      for (int i = s.length() - 2; i >= 0; i--) {
        String sub = s.substring(i, i + 2);
        String repl = rules.get(sub);
        if (repl != null) {
          sub = "" + sub.charAt(0) + repl + sub.charAt(1);
        }
        s = s.substring(0, i) + sub + s.substring(i + 2, s.length());
      }
      System.out.println(s);
    }

    //    System.out.println(s.chars().mapToObj(c -> ""+(char)c).toList());
    Map<String, Long> freq = frequencyMap(s.chars().mapToObj(c -> "" + (char) c));
    String mostCommon = "";
    long mostCommonVal = 0;
    String leastCommon = "";
    long leastCommonVal = Integer.MAX_VALUE;

    for (Map.Entry<String, Long> entry : freq.entrySet()) {
      if (entry.getValue() > mostCommonVal) {
        mostCommonVal = entry.getValue();
        mostCommon = entry.getKey();
      }
      if (entry.getValue() < leastCommonVal) {
        leastCommonVal = entry.getValue();
        leastCommon = entry.getKey();
      }
    }

    System.out.println(mostCommonVal - leastCommonVal);
    System.out.println(freq);
    return 0;
  }

  public int part2() {
    Map<String, BigInteger> freqs = new HashMap<>();
    for (int i = 0; i < start.length() - 1; i++) {
      String sub = start.substring(i, i + 2);
      freqs.put(sub, freqs.getOrDefault(sub, BigInteger.ZERO).add(BigInteger.ONE));
    }

    for (int r = 0; r < 40; r++) {
      Map<String, BigInteger> newFreqs = new HashMap<>();
      for (Map.Entry<String, BigInteger> entry : freqs.entrySet()) {

        String toInsert = rules.get(entry.getKey());
        String s1 = entry.getKey().charAt(0) + toInsert;
        String s2 = toInsert + entry.getKey().charAt(1);

        if (toInsert != null) {
          newFreqs.put(s1, newFreqs.getOrDefault(s1, BigInteger.ZERO).add(entry.getValue()));
          newFreqs.put(s2, newFreqs.getOrDefault(s2, BigInteger.ZERO).add(entry.getValue()));
        }
      }
      freqs = newFreqs;
    }

    Map<Character, List<Map.Entry<String, BigInteger>>> countMap =
        freqs.entrySet().stream().collect(Collectors.groupingBy(e -> e.getKey().charAt(1)));

    Map<Object, String> freqMap =
        countMap.entrySet().stream()
            .map(
                e ->
                    new AbstractMap.SimpleEntry(
                        e.getKey(),
                        e.getValue().stream()
                            .map(a -> a.getValue())
                            .reduce(BigInteger.ZERO, BigInteger::add)))
            .collect(Collectors.toMap(e -> e.getKey(), e -> String.valueOf(e.getValue())));

    System.out.println(
        freqMap.values().stream()
            .map(BigInteger::new)
            .max(BigInteger::compareTo)
            .get()
            .subtract(
                freqMap.values().stream().map(BigInteger::new).min(BigInteger::compareTo).get()));
    return 0;
  }
}
