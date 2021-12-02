package com.github.berry120.adventofcode_2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day3Test {

  private Day3 day3;

  @BeforeEach
  void setup() {
    day3 = new Day3(Input.getInput(2020,"day3"));
  }

  @Test
  void part1() {
    assertEquals(299L, day3.part1());
  }

  @Test
  void part2() {
    assertEquals(3621285278L, day3.part2());
  }
}