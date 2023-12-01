package com.github.berry120.adventofcode_2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day4Test {

  private Day4 day;

  @BeforeEach
  void setup() {
    day = new Day4(Input.getInput(2022,"day4"));
  }

  @Test
  void part1() {
    assertEquals(413, day.part1());
  }

  @Test
  void part2() {
    assertEquals(806, day.part2());
  }
}