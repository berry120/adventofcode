package com.github.berry120.adventofcode_2021;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day9Test {

  private Day9 day;

  @BeforeEach
  void setup() {
    day = new Day9(Input.getInput(2021, "day9"));
  }

  @Test
  void part1() {
    assertEquals(530, day.part1());
  }

  @Test
  void part2() {
    assertEquals(1019494, day.part2());
  }
}
