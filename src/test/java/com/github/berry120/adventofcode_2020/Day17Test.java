package com.github.berry120.adventofcode_2020;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day17Test {

  private Day17 day17;

  @BeforeEach
  void setup() {
    day17 = new Day17(Input.getInput("day17"));
  }

  @Test
  void part1() {
    System.out.println(day17.part1());
  }

  @Test
  void part2() {
    System.out.println(day17.part2());
  }
}