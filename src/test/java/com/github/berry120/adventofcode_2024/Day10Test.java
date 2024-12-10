package com.github.berry120.adventofcode_2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day10Test {

  private Day10 day10;

  @BeforeEach
  void setup() {
    day10 = new Day10(Input.getInput(2024, "day10"));
  }

  @Test
  void part1() {
    assertEquals(496, day10.part1());
  }

  @Test
  void part2() {
    assertEquals(1120, day10.part2());
  }
}
