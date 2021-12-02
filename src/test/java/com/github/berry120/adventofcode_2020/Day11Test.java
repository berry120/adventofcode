package com.github.berry120.adventofcode_2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day11Test {

  private Day11 day11;

  @BeforeEach
  void setup() {
    day11 = new Day11(Input.getInput(2020,"day11"));
  }

  @Test
  void part1() {
    assertEquals(2412, day11.part1());
  }

  @Test
  void part2() {
    assertEquals(2176, day11.part2());
  }
}