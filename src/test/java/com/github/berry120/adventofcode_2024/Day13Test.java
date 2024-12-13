package com.github.berry120.adventofcode_2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day13Test {

  private Day13 day13;

  @BeforeEach
  void setup() {
    day13 = new Day13(Input.getInput(2024, "day13"));
  }

  @Test
  void part1() {
    assertEquals(33427, day13.part1());
  }

  @Test
  void part2() {
    assertEquals(91649162972270L, day13.part2());
  }
}
