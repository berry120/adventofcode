package com.github.berry120.adventofcode_2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day15Test {

  private Day15 day15;

  @BeforeEach
  void setup() {
    day15 = new Day15(Input.getInput(2024, "day15"));
  }

  @Test
  void part1() {
    assertEquals(1436690, day15.part1());
  }

  @Test
  void part2() {
    assertEquals(1482350, day15.part2());
  }
}
