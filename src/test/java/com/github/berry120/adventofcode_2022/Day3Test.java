package com.github.berry120.adventofcode_2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day3Test {

  private Day3 day;

  @BeforeEach
  void setup() {
    day = new Day3(Input.getInput(2022,"day3"));
  }

  @Test
  void part1() {
    assertEquals(7701, day.part1());
  }

  @Test
  void part2() {
    assertEquals(2644, day.part2());
  }
}