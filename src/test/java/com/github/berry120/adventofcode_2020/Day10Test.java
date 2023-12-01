package com.github.berry120.adventofcode_2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day10Test {

  private Day10 day10;

  @BeforeEach
  void setup() {
    day10 = new Day10(Input.getInput(2020, "day10"));
  }

  @Test
  void part1() {
    assertEquals(2380, day10.part1());
  }

  @Test
  void part2() {
    assertEquals(48358655787008L, day10.part2());
  }
}
