package com.github.berry120.adventofcode_2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day11Test {

  private Day11 day;

  @BeforeEach
  void setup() {
    day = new Day11(Input.getInput(2022,"day11"));
  }

  @Test
  void part1() {
    assertEquals(120056, day.part1());
  }

  @Test
  void part2() {
    assertEquals(21816744824L, day.part2());
  }
}