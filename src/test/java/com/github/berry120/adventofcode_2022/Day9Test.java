package com.github.berry120.adventofcode_2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day9Test {

  private Day9 day;

  @BeforeEach
  void setup() {
    day = new Day9(Input.getInput(2022,"day9"));
  }

  @Test
  void part1() {
    assertEquals(6037, day.part1());
  }

  @Test
  void part2() {
    assertEquals(2485, day.part2());
  }
}