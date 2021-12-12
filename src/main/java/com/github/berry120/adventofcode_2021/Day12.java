package com.github.berry120.adventofcode_2021;

import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
          if (!nextChoice.equals("end") && nextChoice.equals(nextChoice.toLowerCase())
              && containsTwice(path) && path.contains(nextChoice)) {
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
    Map<String, Long> freq = frequencyMap(path.stream().filter(s->s.equals(s.toLowerCase())));
    for(String str : freq.keySet()) {
      if(freq.get(str)>=2) {
//        System.out.println(path);
        return true;
      }
    }
    return false;
//
//    int x = 0;
//    for(String str : path) {
//      if(str.equals(choice)) {
//        x++;
//        if(x>=2) break;
//      }
//    }
//    return x>=2;
  }

  <T> Map<T, Long>  frequencyMap(Stream<T> elements) {
    return elements.collect(
            Collectors.groupingBy(
                    Function.identity(),
                    HashMap::new, // can be skipped
                    Collectors.counting()
            )
    );
  }

  boolean test(List<List<String>> paths) {
    for (List<String> path : paths) {
      if (!path.get(path.size() - 1).equals("end")) {
        return false;
      }
    }
    return true;
  }

  public int part1() {
    List<List<String>> paths = new ArrayList<>();
    paths.add(List.of("start"));

    while (!test(paths)) {
      paths = getRoutes1(paths);
    }

    return paths.size();
  }

  public int part2() {
    List<List<String>> paths = new ArrayList<>();
    paths.add(List.of("start"));

    while (!test(paths)) {
      paths = getRoutes2(paths);
    }

    return paths.size();
  }
}
