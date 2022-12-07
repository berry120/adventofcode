package com.github.berry120.adventofcode_2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day7Test {

  private Day7 day;

  @BeforeEach
  void setup() {
    day = new Day7(Input.getInput(2022,"day7"));
  }

  @Test
  void part1() {
    assertEquals(2104783, day.part1());
  }

  @Test
  void part2() {
    assertEquals(5883165, day.part2());
  }
}