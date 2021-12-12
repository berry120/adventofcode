package com.github.berry120.adventofcode_2021;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {

  private Day12 day;

  @BeforeEach
  void setup() {
    day = new Day12(Input.getInput(2021,"day12"));
  }

  @Test
  void part1() {
    assertEquals(5254, day.part1());
  }

  @Test
  void part2() {
    assertEquals(149385, day.part2());
  }
}