package com.github.berry120.adventofcode_2021;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day15Test {

  private Day15 day;

  @BeforeEach
  void setup() {
    day = new Day15(Input.getInput(2021,"day15"));
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