package com.github.berry120.adventofcode_2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day9Test {

  private Day9 day9;

  @BeforeEach
  void setup() {
    day9 = new Day9(Input.getInput(2020,"day9"));
  }

  @Test
  void part1() {
    assertEquals(400480901L, day9.part1());
  }

  @Test
  void part2() {
    assertEquals(67587168, day9.part2());
  }
}