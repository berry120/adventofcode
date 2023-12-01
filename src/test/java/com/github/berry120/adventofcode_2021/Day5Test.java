package com.github.berry120.adventofcode_2021;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {

  private Day5 day5;

  @BeforeEach
  void setup() {
    day5 = new Day5(Input.getInput(2021,"day5"));
  }

  @Test
  void part1() {
    assertEquals(1, day5.part1());
  }

  @Test
  void part2() {
    assertEquals(1, day5.part2());
  }
}