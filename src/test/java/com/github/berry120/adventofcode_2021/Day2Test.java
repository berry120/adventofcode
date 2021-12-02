package com.github.berry120.adventofcode_2021;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

  private Day2 day2;

  @BeforeEach
  void setup() {
    day2 = new Day2(Input.getInput(2021,"day2"));
  }

  @Test
  void part1() {
    assertEquals(1692075, day2.part1());
  }

  @Test
  void part2() {
    assertEquals(1749524700, day2.part2());
  }
}