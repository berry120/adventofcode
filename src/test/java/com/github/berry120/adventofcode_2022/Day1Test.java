package com.github.berry120.adventofcode_2022;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {

  private Day1 day1;

  @BeforeEach
  void setup() {
    day1 = new Day1(Input.getInput(2022,"day1"));
  }

  @Test
  void part1() {
    assertEquals(69501, day1.part1());
  }

  @Test
  void part2() {
    assertEquals(202346, day1.part2());
  }
}