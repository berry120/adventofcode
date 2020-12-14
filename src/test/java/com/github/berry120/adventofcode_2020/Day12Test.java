package com.github.berry120.adventofcode_2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.adventofcode_2020.input.Input;
import java.math.BigInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day12Test {

  private Day12 day12;

  @BeforeEach
  void setup() {
    day12 = new Day12(Input.getInput("day12"));
  }

  @Test
  void part1() {
    assertEquals(1186, day12.part1());
  }

  @Test
  void part2() {
    assertEquals(47806, day12.part2());
  }
}