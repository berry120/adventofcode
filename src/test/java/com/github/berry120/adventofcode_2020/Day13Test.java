package com.github.berry120.adventofcode_2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day13Test {

  private Day13 day13;

  @BeforeEach
  void setup() {
    day13 = new Day13(Input.getInput("day13"));
  }

  @Test
  void part1() {
    assertEquals(5875750429995L, day13.part1());
  }

  @Test
  void part2() {
    assertEquals(5272149590143L, day13.part2());
  }
}