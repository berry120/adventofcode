package com.github.berry120.adventofcode_2021;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day14Test {

  private Day14 day;

  @BeforeEach
  void setup() {
    day = new Day14(Input.getInput(2021,"day14"));
  }

  @Test
  void part1() {
    assertEquals(0, day.part1());
  }

  @Test
  void part2() {
    assertEquals(0, day.part2());
  }
}