package com.github.berry120.adventofcode_2021;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day17Test {

  private Day17 day;

  @BeforeEach
  void setup() {
    day = new Day17(Input.getInput(2021,"day17"));
  }

  @Test
  void part1() {
    assertEquals(33670, day.part1());
  }

  @Test
  void part2() {
    assertEquals(4903, day.part2());
  }
}