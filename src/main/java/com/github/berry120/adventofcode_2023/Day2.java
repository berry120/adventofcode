package com.github.berry120.adventofcode_2023;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day2 {

  private final List<Game> games;

  public Day2(String input) {
    games = new ArrayList<>();
    for (String line : input.split("\n")) {
      int id = Integer.parseInt(line.split(":")[0].substring(5).trim());
      String gameList = line.split(":")[1].trim();
      String[] cubesets = gameList.split(";");
      List<CubeSet> cubeSetList = new ArrayList<>();
      for (String cube : cubesets) {
        String[] numAndColours = cube.split(",");
        List<NumAndColour> numAndColourList = new ArrayList<>();
        for (String numAndColour : numAndColours) {
          numAndColour = numAndColour.trim();
          NumAndColour n =
              new NumAndColour(
                  Integer.parseInt(numAndColour.split(" ")[0]), numAndColour.split(" ")[1]);
          numAndColourList.add(n);
        }
        cubeSetList.add(new CubeSet(numAndColourList));
      }
      games.add(new Game(id, cubeSetList));
    }
  }

  public int part1() {
    Set<Game> gameSet = new HashSet<>(games);
    for (Game game : games) {
      for (CubeSet cubeSet : game.moves()) {
        for (NumAndColour x : cubeSet.cubes()) {
          if (x.colour().equals("red") && x.num() > 12) {
            gameSet.remove(game);
          }
          if (x.colour().equals("green") && x.num() > 13) {
            gameSet.remove(game);
          }
          if (x.colour().equals("blue") && x.num() > 14) {
            gameSet.remove(game);
          }
        }
      }
    }
    return gameSet.stream().mapToInt(Game::id).sum();
  }

  public int part2() {
    int sum=0;
    for (Game game : games) {
      int power =
          game.moves().stream()
              .reduce(
                  (a, b) -> {
                    int numRedA = numFromColour(a, "red");
                    int numRedB = numFromColour(b, "red");
                    int numGreenA = numFromColour(a, "green");
                    int numGreenB = numFromColour(b, "green");
                    int numBlueA = numFromColour(a, "blue");
                    int numBlueB = numFromColour(b, "blue");

                    return new CubeSet(
                        List.of(
                            new NumAndColour(Math.max(numRedA, numRedB), "red"),
                            new NumAndColour(Math.max(numGreenA, numGreenB), "green"),
                            new NumAndColour(Math.max(numBlueA, numBlueB), "blue")));
                  })
              .map(
                  x -> {
                    System.out.println(x);
                    return x;
                  })
              .map(
                  cs ->
                      cs.cubes().stream()
                              .filter(x -> x.colour.equals("green"))
                              .mapToInt(NumAndColour::num)
                              .findAny()
                              .orElse(0)
                          * cs.cubes().stream()
                              .filter(x -> x.colour.equals("red"))
                              .mapToInt(NumAndColour::num)
                              .findAny()
                              .orElse(0)
                          * cs.cubes().stream()
                              .filter(x -> x.colour.equals("blue"))
                              .mapToInt(NumAndColour::num)
                              .findAny()
                              .orElse(0))
              .orElseThrow();
      sum+=power;
    }
    return sum;
  }

  private int numFromColour(CubeSet cubeSet, String colour) {
    return cubeSet.cubes().stream()
        .filter(x -> x.colour().equals(colour))
        .mapToInt(NumAndColour::num)
        .findAny()
        .orElse(0);
  }

  record Game(int id, List<CubeSet> moves) {}

  record CubeSet(List<NumAndColour> cubes) {}

  record NumAndColour(int num, String colour) {}
}
