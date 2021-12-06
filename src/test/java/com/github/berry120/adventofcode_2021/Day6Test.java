package com.github.berry120.adventofcode_2021;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {

  private Day6 day6;

  @BeforeEach
  void setup() {
    day6 = new Day6(Input.getInput(2021, "day6"));
  }

  @Test
  void part1() {
    assertEquals(356190, day6.part1());
  }

  @Test
  void part2() {
    assertEquals(1617359101538L, day6.part2());
  }
}
