package com.github.berry120.adventofcode_2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day14Test {

  private Day14 day14;

  @BeforeEach
  void setup() {
    day14 = new Day14(Input.getInput(2024, "day14"));
  }

  @Test
  void part1() {
    assertEquals(225810288, day14.part1());
  }

  @Test
  void part2() {
    assertEquals(6752, day14.part2());
  }
}
