package com.github.berry120.adventofcode_2020;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day17 {

  private final String input;

  static class State4D {

    Map<Integer, Map<Integer, Map<Integer, Map<Integer, Boolean>>>> state;

    public State4D() {
      state = new HashMap<>();
    }

    public State4D(State4D state4D) {
      this.state = copy(state4D.state);
    }

    public int active() {
      int active = 0;
      for (var value : state.values()) {
        for (var value2 : value.values()) {
          for (var value3 : value2.values()) {
            for (var value4 : value3.values()) {
              if (value4) {
                active++;
              }
            }
          }
        }
      }
      return active;
    }

    public static Map<Integer, Map<Integer, Map<Integer, Map<Integer, Boolean>>>> copy(
        Map<Integer, Map<Integer, Map<Integer, Map<Integer, Boolean>>>> original) {
      Map<Integer, Map<Integer, Map<Integer, Map<Integer, Boolean>>>> copy = new HashMap<>();
      for (Map.Entry<Integer, Map<Integer, Map<Integer, Map<Integer, Boolean>>>> entry : original
          .entrySet()) {
        copy.put(entry.getKey(), copyH(entry.getValue()));
      }
      return copy;
    }

    public static Map<Integer, Map<Integer, Map<Integer, Boolean>>> copyH(
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

    boolean get(int i, int j, int k, int l) {
      return state.getOrDefault(i, new HashMap<>()).getOrDefault(j, new HashMap<>())
          .getOrDefault(k, new HashMap<>())
          .getOrDefault(l, false);
    }

    void set(int i, int j, int k, int l, boolean val) {
      var map = state.getOrDefault(i, new HashMap<>());
      var map2 = map.getOrDefault(j, new HashMap<>());
      var map3 = map2.getOrDefault(k, new HashMap<>());
      map3.put(l, val);
      map2.put(k, map3);
      map.put(j, map2);
      state.put(i, map);
    }

  }

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

    void set(int i, int j, int k, boolean val) {
      var map = state.getOrDefault(i, new HashMap<>());
      var map2 = map.getOrDefault(j, new HashMap<>());
      map2.put(k, val);
      map.put(j, map2);
      state.put(i, map);
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

    return state.active();
  }


  public int part2() {
    State4D state4D = new State4D();

    String[] lines = input.split("\n");
    for (int i = 0; i < lines.length; i++) {
      char[] chars = lines[i].toCharArray();
      for (int j = 0; j < chars.length; j++) {
        state4D.set(i, j, 0, 0, chars[j] == '#');
      }
    }

    for (int iter = 0; iter < 6; iter++) {

      var staticState = new State4D(state4D);
      for (int i = -20; i < 20; i++) {
        for (int j = -20; j < 20; j++) {
          for (int k = -20; k < 20; k++) {
            for (int l = -20; l < 20; l++) {

              boolean updated = getUpdatedState(
                  staticState.get(i, j, k, l),

                  staticState.get(i, j, k + 1, l),
                  staticState.get(i, j, k - 1, l),
                  staticState.get(i, j + 1, k + 1, l),
                  staticState.get(i, j + 1, k, l),
                  staticState.get(i, j + 1, k - 1, l),
                  staticState.get(i, j - 1, k + 1, l),
                  staticState.get(i, j - 1, k, l),
                  staticState.get(i, j - 1, k - 1, l),
                  staticState.get(i - 1, j, k, l),
                  staticState.get(i - 1, j, k + 1, l),
                  staticState.get(i - 1, j, k - 1, l),
                  staticState.get(i - 1, j + 1, k + 1, l),
                  staticState.get(i - 1, j + 1, k, l),
                  staticState.get(i - 1, j + 1, k - 1, l),
                  staticState.get(i - 1, j - 1, k + 1, l),
                  staticState.get(i - 1, j - 1, k, l),
                  staticState.get(i - 1, j - 1, k - 1, l),
                  staticState.get(i + 1, j, k, l),
                  staticState.get(i + 1, j, k + 1, l),
                  staticState.get(i + 1, j, k - 1, l),
                  staticState.get(i + 1, j + 1, k + 1, l),
                  staticState.get(i + 1, j + 1, k, l),
                  staticState.get(i + 1, j + 1, k - 1, l),
                  staticState.get(i + 1, j - 1, k + 1, l),
                  staticState.get(i + 1, j - 1, k, l),
                  staticState.get(i + 1, j - 1, k - 1, l),
                  staticState.get(i, j, k, l + 1),
                  staticState.get(i, j, k + 1, l + 1),
                  staticState.get(i, j, k - 1, l + 1),
                  staticState.get(i, j + 1, k + 1, l + 1),
                  staticState.get(i, j + 1, k, l + 1),
                  staticState.get(i, j + 1, k - 1, l + 1),
                  staticState.get(i, j - 1, k + 1, l + 1),
                  staticState.get(i, j - 1, k, l + 1),
                  staticState.get(i, j - 1, k - 1, l + 1),
                  staticState.get(i - 1, j, k, l + 1),
                  staticState.get(i - 1, j, k + 1, l + 1),
                  staticState.get(i - 1, j, k - 1, l + 1),
                  staticState.get(i - 1, j + 1, k + 1, l + 1),
                  staticState.get(i - 1, j + 1, k, l + 1),
                  staticState.get(i - 1, j + 1, k - 1, l + 1),
                  staticState.get(i - 1, j - 1, k + 1, l + 1),
                  staticState.get(i - 1, j - 1, k, l + 1),
                  staticState.get(i - 1, j - 1, k - 1, l + 1),
                  staticState.get(i + 1, j, k, l + 1),
                  staticState.get(i + 1, j, k + 1, l + 1),
                  staticState.get(i + 1, j, k - 1, l + 1),
                  staticState.get(i + 1, j + 1, k + 1, l + 1),
                  staticState.get(i + 1, j + 1, k, l + 1),
                  staticState.get(i + 1, j + 1, k - 1, l + 1),
                  staticState.get(i + 1, j - 1, k + 1, l + 1),
                  staticState.get(i + 1, j - 1, k, l + 1),
                  staticState.get(i + 1, j - 1, k - 1, l + 1),
                  staticState.get(i, j, k, l-1),
                  staticState.get(i, j, k + 1, l-1),
                  staticState.get(i, j, k - 1, l-1),
                  staticState.get(i, j + 1, k + 1, l-1),
                  staticState.get(i, j + 1, k, l-1),
                  staticState.get(i, j + 1, k - 1, l-1),
                  staticState.get(i, j - 1, k + 1, l-1),
                  staticState.get(i, j - 1, k, l-1),
                  staticState.get(i, j - 1, k - 1, l-1),
                  staticState.get(i - 1, j, k, l-1),
                  staticState.get(i - 1, j, k + 1, l-1),
                  staticState.get(i - 1, j, k - 1, l-1),
                  staticState.get(i - 1, j + 1, k + 1, l-1),
                  staticState.get(i - 1, j + 1, k, l-1),
                  staticState.get(i - 1, j + 1, k - 1, l-1),
                  staticState.get(i - 1, j - 1, k + 1, l-1),
                  staticState.get(i - 1, j - 1, k, l-1),
                  staticState.get(i - 1, j - 1, k - 1, l-1),
                  staticState.get(i + 1, j, k, l-1),
                  staticState.get(i + 1, j, k + 1, l-1),
                  staticState.get(i + 1, j, k - 1, l-1),
                  staticState.get(i + 1, j + 1, k + 1, l-1),
                  staticState.get(i + 1, j + 1, k, l-1),
                  staticState.get(i + 1, j + 1, k - 1, l-1),
                  staticState.get(i + 1, j - 1, k + 1, l-1),
                  staticState.get(i + 1, j - 1, k, l-1),
                  staticState.get(i + 1, j - 1, k - 1, l-1)
              );

              state4D.set(i, j, k, l, updated);
            }
          }
        }
      }
    }

    return state4D.active();
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

}
