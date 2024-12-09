package com.github.berry120.adventofcode_2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day9Test {

  private Day9 day9;

  @BeforeEach
  void setup() {
    day9 = new Day9(Input.getInput(2024, "day9"));
  }

  @Test
  void part1() {
    assertEquals(6346871685398L, day9.part1());
  }

  @Test
  void part2() {
    assertEquals(6373055193464L, day9.part2());
  }
}
