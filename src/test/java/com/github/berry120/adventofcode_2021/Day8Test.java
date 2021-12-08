package com.github.berry120.adventofcode_2021;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day8Test {

  private Day8 day;

  @BeforeEach
  void setup() {
    day = new Day8(Input.getInput(2021, "day8"));
  }

  @Test
  void part1() {
    assertEquals(0, day.part1());
  }

  @Test
  void part2() {
    assertEquals(1028926, day.part2());
  }
}
