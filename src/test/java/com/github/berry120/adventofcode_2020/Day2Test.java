package com.github.berry120.adventofcode_2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day2Test {

  private Day2 day2;

  @BeforeEach
  void setup() {
    day2 = new Day2(Input.getInput("day2"));
  }

  @Test
  void part1() {
    assertEquals(398, day2.part1());
  }

  @Test
  void part2() {
    assertEquals(562, day2.part2());
  }
}