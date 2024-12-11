package com.github.berry120.adventofcode_2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day11Test {

  private Day11 day11;

  @BeforeEach
  void setup() {
    day11 = new Day11(Input.getInput(2024, "day11"));
  }

  @Test
  void part1() {
    assertEquals(213625L, day11.part1());
  }

  @Test
  void part2() {
    assertEquals(252442982856820L, day11.part2());
  }
}
