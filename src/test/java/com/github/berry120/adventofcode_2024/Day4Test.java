package com.github.berry120.adventofcode_2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day4Test {

  private Day4 day4;

  @BeforeEach
  void setup() {
    day4 = new Day4(Input.getInput(2024,"day4"));
  }

  @Test
  void part1() {
    assertEquals(2654, day4.part1());
  }

  @Test
  void part2() {
    assertEquals(1990, day4.part2());
  }
}