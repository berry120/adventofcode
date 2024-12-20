package com.github.berry120.adventofcode_2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day7Test {

  private Day7 day7;

  @BeforeEach
  void setup() {
    day7 = new Day7(Input.getInput(2024, "day7"));
  }

  @Test
  void part1() {
    assertEquals(66343330034722L, day7.part1());
  }

  @Test
  void part2() {
    assertEquals(637696070419031L, day7.part2());
  }
}
