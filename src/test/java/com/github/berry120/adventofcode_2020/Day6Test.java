package com.github.berry120.adventofcode_2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day6Test {

  private Day6 day6;

  @BeforeEach
  void setup() {
    day6 = new Day6(Input.getInput("day6"));
  }

  @Test
  void part1() {
    assertEquals(6748, day6.part1());
  }

  @Test
  void part2() {
    assertEquals(3445, day6.part2());
  }
}