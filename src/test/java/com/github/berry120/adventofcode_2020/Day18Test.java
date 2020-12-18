package com.github.berry120.adventofcode_2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day18Test {

  private Day18 day18;

  @BeforeEach
  void setup() {
    day18 = new Day18(Input.getInput("day18"));
  }

  @Test
  void part1() {
    assertEquals(12956356593940L, day18.part1());
  }

  @Test
  void part2() {
    assertEquals(94240043727614L, day18.part2());
  }
}