package com.github.berry120.adventofcode_2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day17Test {

  private Day17 day17;

  @BeforeEach
  void setup() {
    day17 = new Day17(Input.getInput(2020,"day17"));
  }

  @Test
  void part1() {
    assertEquals(271, day17.part1());
  }

  @Test
  void part2() {
    assertEquals(2064, day17.part2());
  }
}