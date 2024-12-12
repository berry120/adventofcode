package com.github.berry120.adventofcode_2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day12Test {

  private Day12 day12;

  @BeforeEach
  void setup() {
    day12 = new Day12(Input.getInput(2024, "day12"));
  }

  @Test
  void part1() {
    assertEquals(1489582, day12.part1());
  }

  @Test
  void part2() {
    assertEquals(914966, day12.part2());
  }
}
