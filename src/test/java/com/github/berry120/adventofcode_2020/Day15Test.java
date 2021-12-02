package com.github.berry120.adventofcode_2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day15Test {

  private Day15 day15;

  @BeforeEach
  void setup() {
    day15 = new Day15(Input.getInput(2020,"day15"));
  }

  @Test
  void part1() {
    assertEquals(929, day15.part1());
  }

  @Test
  void part2() {
    assertEquals(16671510, day15.part2());
  }
}