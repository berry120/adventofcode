package com.github.berry120.adventofcode_2021;

import lombok.AllArgsConstructor;
import lombok.With;
import reactor.core.publisher.Flux;

@AllArgsConstructor
public class Day2 {

  private final String input;

  public int part1() {
    return Flux.fromStream(input.lines())
        .reduce(
            new State(0, 0, 0),
            (s, cmd) ->
                cmd.startsWith("f")
                    ? s.withPos(s.pos() + num(cmd))
                    : s.withDepth(s.depth() + (cmd.startsWith("d") ? num(cmd) : -num(cmd))))
        .map(s -> s.pos() * s.depth())
        .block();
  }

  public int part2() {
    return Flux.fromStream(input.lines())
        .reduce(
            new State(0, 0, 0),
            (s, cmd) ->
                cmd.startsWith("f")
                    ? s.withPos(s.pos() + num(cmd)).withDepth(s.depth() + (s.aim() * num(cmd)))
                    : s.withAim(s.aim() + (cmd.startsWith("d") ? num(cmd) : -num(cmd))))
        .map(s -> s.pos() * s.depth())
        .block();
  }

  private int num(String command) {
    return Integer.parseInt(command.split(" ")[1]);
  }

  @With
  record State(int pos, int depth, int aim) {}
}
