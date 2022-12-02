package com.github.berry120.adventofcode_2022;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Test {

  private Day2 day1;

  @BeforeEach
  void setup() {
    day1 = new Day2(Input.getInput(2022,"day2"));
  }

  @Test
  void part1() {
    assertEquals(11386, day1.part1());
  }

  @Test
  void part2() {
    assertEquals(13600, day1.part2());
  }
}