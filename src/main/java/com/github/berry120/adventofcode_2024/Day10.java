package com.github.berry120.adventofcode_2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day10 {

  private Node[][] nodeGrid;

  record Node(UUID id, int num, List<Node> connections) {}

  public Day10(String input) {
    nodeGrid = new Node[input.split("\n").length][input.split("\n")[0].length()];
    String[] lines = input.split("\n");
    for (int i = 0; i < lines.length; i++) {
      for (int j = 0; j < lines[0].length(); j++) {
        nodeGrid[i][j] =
            new Node(
                UUID.randomUUID(),
                Character.getNumericValue(lines[i].charAt(j)),
                new ArrayList<>());
      }
    }
    for (int i = 0; i < lines.length; i++) {
      for (int j = 0; j < lines[0].length(); j++) {
        if (i > 0 && nodeGrid[i - 1][j].num() == nodeGrid[i][j].num() + 1)
          nodeGrid[i][j].connections().add(nodeGrid[i - 1][j]);
        if (j > 0 && nodeGrid[i][j - 1].num() == nodeGrid[i][j].num() + 1)
          nodeGrid[i][j].connections().add(nodeGrid[i][j - 1]);
        if (i < lines.length - 1 && nodeGrid[i + 1][j].num() == nodeGrid[i][j].num() + 1)
          nodeGrid[i][j].connections().add(nodeGrid[i + 1][j]);
        if (j < lines[0].length() - 1 && nodeGrid[i][j + 1].num() == nodeGrid[i][j].num() + 1)
          nodeGrid[i][j].connections().add(nodeGrid[i][j + 1]);
      }
    }
  }

  public long part1() {
    return countTrails(true);
  }

  public long part2() {
    return countTrails(false);
  }

  private long countTrails(boolean distinct) {
    return Arrays.stream(nodeGrid)
        .flatMap(Arrays::stream)
        .filter(n -> n.num == 0)
        .map(node -> flatChildren(node).filter(n -> n.num() == 9))
        .mapToLong(s -> distinct ? s.distinct().count() : s.count())
        .sum();
  }

  private Stream<Node> flatChildren(Node node) {
    return Stream.concat(
        node.connections().stream(), node.connections().stream().flatMap(this::flatChildren));
  }
}
