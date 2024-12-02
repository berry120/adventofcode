package com.github.berry120.adventofcode_2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day1Test {

  private Day1 day1;

  @BeforeEach
  void setup() {
    day1 = new Day1(Input.getInput(2024,"day1"));
  }

  @Test
  void part1() {
    assertEquals(2742123, day1.part1());
  }

  @Test
  void part2() {
    assertEquals(21328497, day1.part2());
  }
}