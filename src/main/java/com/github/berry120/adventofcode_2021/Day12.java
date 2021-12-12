package com.github.berry120.adventofcode_2021;

import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Day12 {

  private final Map<String, List<String>> adjMap;

  public Day12(String input) {
    adjMap = new HashMap<>();

    for (String line : input.split("\n")) {
      String a = line.split("-")[0];
      String b = line.split("-")[1];

      if (!a.equals("end")) {
        List<String> results = adjMap.getOrDefault(a, new ArrayList<>());
        if (!b.equals("start")) {
          results.add(b);
        }
        adjMap.put(a, results);
      }

      if (!b.equals("end")) {
        List<String> results = adjMap.getOrDefault(b, new ArrayList<>());
        if (!a.equals("start")) {
          results.add(a);
        }
        adjMap.put(b, results);
      }
    }
  }

  List<List<String>> getRoutes1(List<List<String>> startPaths) {

    List<List<String>> ret = new ArrayList<>();
    for (List<String> path : startPaths) {
      String last = path.get(path.size() - 1);
      if (last.equals("end")) {
        ret.add(path);
      } else {
        for (String nextChoice : adjMap.get(last)) {
          if (nextChoice.equals(nextChoice.toLowerCase()) && path.contains(nextChoice)) {
            continue;
          }
          List<String> newPath = new ArrayList<>(path);
          newPath.add(nextChoice);
          ret.add(newPath);
        }
      }
    }
    return ret;
  }

  List<List<String>> getRoutes2(List<List<String>> startPaths) {

    List<List<String>> ret = new ArrayList<>();
    for (List<String> path : startPaths) {
      String last = path.get(path.size() - 1);
      if (last.equals("end")) {
        ret.add(path);
      } else {
        for (String nextChoice : adjMap.get(last)) {
          if (!nextChoice.equals("end")
              && nextChoice.equals(nextChoice.toLowerCase())
              && containsTwice(path)
              && path.contains(nextChoice)) {
            continue;
          }
          List<String> newPath = new ArrayList<>(path);
          newPath.add(nextChoice);
          ret.add(newPath);
        }
      }
    }
    return ret;
  }

  boolean containsTwice(List<String> path) {
    Map<String, Long> freq =
        path.stream()
            .filter(s -> s.equals(s.toLowerCase()))
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    return freq.values().stream().anyMatch(n->n>=2);
  }

  boolean hasIncompletePaths(List<List<String>> paths) {
    return paths.stream().anyMatch(path -> !path.get(path.size() - 1).equals("end"));
  }

  public int part1() {
    List<List<String>> paths = new ArrayList<>();
    paths.add(List.of("start"));

    while (hasIncompletePaths(paths)) {
      paths = getRoutes1(paths);
    }

    return paths.size();
  }

  public int part2() {
    List<List<String>> paths = new ArrayList<>();
    paths.add(List.of("start"));

    while (hasIncompletePaths(paths)) {
      paths = getRoutes2(paths);
    }

    return paths.size();
  }
}
