package com.github.berry120.adventofcode_2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day6Test {

  private Day6 day6;

  @BeforeEach
  void setup() {
    day6 = new Day6(Input.getInput(2024, "day6"));
  }

  @Test
  void part1() {
    assertEquals(5030, day6.part1());
  }

  @Test
  void part2() {
    assertEquals(1928, day6.part2());
  }
}
