package com.github.berry120.adventofcode_2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day5Test {

  private Day5 day;

  @BeforeEach
  void setup() {
    day = new Day5(Input.getInput(2022,"day5"));
  }

  @Test
  void part1() {
    assertEquals("ZBDRNPMVH", day.part1());
  }

  @Test
  void part2() {
    assertEquals("WDLPFNNNB", day.part2());
  }
}