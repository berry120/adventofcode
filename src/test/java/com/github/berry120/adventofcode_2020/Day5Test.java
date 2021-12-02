package com.github.berry120.adventofcode_2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day5Test {

  private Day5 day5;

  @BeforeEach
  void setup() {
    day5 = new Day5(Input.getInput(2020,"day5"));
  }

  @Test
  void part1() {
    assertEquals(930, (day5.part1()));
  }

  @Test
  void part2() {
    assertEquals(515, (day5.part2()));
  }
}