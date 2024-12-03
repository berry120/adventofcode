package com.github.berry120.adventofcode_2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day3Test {

  private Day3 day3;

  @BeforeEach
  void setup() {
    day3 = new Day3(Input.getInput(2024,"day3"));
  }

  @Test
  void part1() {
    assertEquals(175700056, day3.part1());
  }

  @Test
  void part2() {
    assertEquals(71668682, day3.part2());
  }
}