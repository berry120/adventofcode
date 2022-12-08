package com.github.berry120.adventofcode_2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day8Test {

  private Day8 day;

  @BeforeEach
  void setup() {
    day = new Day8(Input.getInput(2022,"day8"));
  }

  @Test
  void part1() {
    assertEquals(1763, day.part1());
  }

  @Test
  void part2() {
    assertEquals(671160, day.part2());
  }
}