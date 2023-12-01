package com.github.berry120.adventofcode_2021;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day3Test {

  private Day3 day3;

  @BeforeEach
  void setup() {
    day3 = new Day3(Input.getInput(2021, "day3"));
  }

  @Test
  void part1() {
    assertEquals(2035764, day3.part1());
  }

  @Test
  void part2() {
    assertEquals(2817661, day3.part2());
  }
}