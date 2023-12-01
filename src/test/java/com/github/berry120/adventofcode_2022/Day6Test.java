package com.github.berry120.adventofcode_2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day6Test {

  private Day6 day;

  @BeforeEach
  void setup() {
    day = new Day6(Input.getInput(2022,"day6"));
  }

  @Test
  void part1() {
    assertEquals(1080, day.part1());
  }

  @Test
  void part2() {
    assertEquals(3645, day.part2());
  }
}