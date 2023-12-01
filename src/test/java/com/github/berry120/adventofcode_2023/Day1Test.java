package com.github.berry120.adventofcode_2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day1Test {

  private Day1 day1;

  @BeforeEach
  void setup() {
    day1 = new Day1(Input.getInput(2023,"day1"));
  }

  @Test
  void part1() {
    assertEquals(55488, day1.part1());
  }

  @Test
  void part2() {
    assertEquals(55614, day1.part2());
  }
}