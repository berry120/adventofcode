package com.github.berry120.adventofcode_2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day5Test {

  private Day5 day5;

  @BeforeEach
  void setup() {
    day5 = new Day5(Input.getInput(2024, "day5"));
  }

  @Test
  void part1() {
    assertEquals(6051, day5.part1());
  }

  @Test
  void part2() {
    assertEquals(5093, day5.part2());
  }
}
