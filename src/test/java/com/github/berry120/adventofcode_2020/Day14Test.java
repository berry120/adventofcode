package com.github.berry120.adventofcode_2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day14Test {

  private Day14 day14;

  @BeforeEach
  void setup() {
    day14 = new Day14(Input.getInput(2020,"day14"));
  }

  @Test
  void part1() {
    assertEquals(5875750429995L, day14.part1());
  }

  @Test
  void part2() {
    assertEquals(5272149590143L, day14.part2());
  }
}