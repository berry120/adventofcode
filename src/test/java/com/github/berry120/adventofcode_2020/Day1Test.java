package com.github.berry120.adventofcode_2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day1Test {

  private Day1 day1;

  @BeforeEach
  void setup() {
    day1 = new Day1(Input.getInput(2020, "day1"));
  }

  @Test
  void part1() {
    assertEquals(719796, day1.part1());
  }

  @Test
  void part2() {
    assertEquals(144554112, day1.part2());
  }
}
