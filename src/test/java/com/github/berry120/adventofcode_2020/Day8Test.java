package com.github.berry120.adventofcode_2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day8Test {

  private Day8 day8;

  @BeforeEach
  void setup() {
    day8 = new Day8(Input.getInput(2020,"day8"));
  }

  @Test
  void part1() {
    assertEquals(1766, day8.part1());
  }

  @Test
  void part2() {
    assertEquals(1639, day8.part2());
  }
}