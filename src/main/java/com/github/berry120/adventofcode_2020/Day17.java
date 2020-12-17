package com.github.berry120.adventofcode_2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day17 {

  private final String input;

  static class State {

    Map<Integer, Map<Integer, Map<Integer, Boolean>>> state;

    public State() {
      state = new HashMap<>();
    }

    public State(State state) {
      this.state = copy(state.state);
    }

    public int active() {
      int active = 0;
      for (var value : state.values()) {
        for (var value2 : value.values()) {
          for (var value3 : value2.values()) {
            if (value3) {
              active++;
            }
          }
        }
      }
      return active;
    }

    public static Map<Integer, Map<Integer, Map<Integer, Boolean>>> copy(
        Map<Integer, Map<Integer, Map<Integer, Boolean>>> original) {
      Map<Integer, Map<Integer, Map<Integer, Boolean>>> copy = new HashMap<>();
      for (Map.Entry<Integer, Map<Integer, Map<Integer, Boolean>>> entry : original.entrySet()) {
        copy.put(entry.getKey(), copyI(entry.getValue()));
      }
      return copy;
    }

    public static Map<Integer, Map<Integer, Boolean>> copyI(
        Map<Integer, Map<Integer, Boolean>> original) {
      Map<Integer, Map<Integer, Boolean>> copy = new HashMap<>();
      for (Map.Entry<Integer, Map<Integer, Boolean>> entry : original.entrySet()) {
        copy.put(entry.getKey(), copyJ(entry.getValue()));
      }
      return copy;
    }

    public static Map<Integer, Boolean> copyJ(
        Map<Integer, Boolean> original) {
      Map<Integer, Boolean> copy = new HashMap<>();
      for (Map.Entry<Integer, Boolean> entry : original.entrySet()) {
        copy.put(entry.getKey(), entry.getValue());
      }
      return copy;
    }

    boolean get(int i, int j, int k) {
      return state.getOrDefault(i, new HashMap<>()).getOrDefault(j, new HashMap<>())
          .getOrDefault(k, false);
    }

    void printSlice(int k) {
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          boolean b = get(i, j, k);
          if (b) {
            System.out.print('#');
          } else {
            System.out.print('.');
          }
        }
        System.out.println();
      }
      System.out.println("---");
    }

    void set(int i, int j, int k, boolean val) {
      var map = state.getOrDefault(i, new HashMap<>());
      var map2 = map.getOrDefault(j, new HashMap<>());
      map2.put(k, val);
      map.put(j, map2);
      state.put(i, map);
    }

    int sizei() {
      return state.size();
    }

    int sizej(int i) {
      return state.getOrDefault(i, new HashMap<>()).size();
    }

    int sizek(int i, int j) {
      return state.getOrDefault(i, new HashMap<>()).getOrDefault(j, new HashMap<>()).size();
    }
  }

  public int part1() {
    State state = new State();

    String[] lines = input.split("\n");
    for (int i = 0; i < lines.length; i++) {
      char[] chars = lines[i].toCharArray();
      for (int j = 0; j < chars.length; j++) {
        state.set(i, j, 0, chars[j] == '#');
      }
    }

//    System.out.println("--------");
//    state.printSlice(0);
//    System.out.println("--------");

    for (int iter = 0; iter < 6; iter++) {
      var staticState = new State(state);
      for (int i = -50; i < 50; i++) {
        for (int j = -50; j < 50; j++) {
          for (int k = -50; k < 50; k++) {

            boolean updated = getUpdatedState(
                staticState.get(i, j, k),

                staticState.get(i, j, k + 1),
                staticState.get(i, j, k - 1),
                staticState.get(i, j + 1, k + 1),
                staticState.get(i, j + 1, k),
                staticState.get(i, j + 1, k - 1),
                staticState.get(i, j - 1, k + 1),
                staticState.get(i, j - 1, k),
                staticState.get(i, j - 1, k - 1),
                staticState.get(i - 1, j, k),
                staticState.get(i - 1, j, k + 1),
                staticState.get(i - 1, j, k - 1),
                staticState.get(i - 1, j + 1, k + 1),
                staticState.get(i - 1, j + 1, k),
                staticState.get(i - 1, j + 1, k - 1),
                staticState.get(i - 1, j - 1, k + 1),
                staticState.get(i - 1, j - 1, k),
                staticState.get(i - 1, j - 1, k - 1),
                staticState.get(i + 1, j, k),
                staticState.get(i + 1, j, k + 1),
                staticState.get(i + 1, j, k - 1),
                staticState.get(i + 1, j + 1, k + 1),
                staticState.get(i + 1, j + 1, k),
                staticState.get(i + 1, j + 1, k - 1),
                staticState.get(i + 1, j - 1, k + 1),
                staticState.get(i + 1, j - 1, k),
                staticState.get(i + 1, j - 1, k - 1)
            );

//            if(k==-1 && j==0 && i==)

            state.set(i, j, k, updated);
          }
        }
      }
    }

//    state.printSlice(-1);
//    state.printSlice(0);
//    state.printSlice(1);

//    System.out.println(state.get(0,0,0));

    return state.active();
  }

  private boolean getUpdatedState(boolean isActive, boolean... around) {
    long numActive = IntStream.range(0, around.length).mapToObj(idx -> around[idx]).filter(b -> b)
        .count();

    if (isActive) {
      return (numActive == 2) || (numActive == 3);
    } else {
      return numActive == 3;
    }
  }

  public int part2() {
    return 0;
  }

}
