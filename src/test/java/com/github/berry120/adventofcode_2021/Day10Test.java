package com.github.berry120.adventofcode_2021;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

  private Day10 day;

  @BeforeEach
  void setup() {
    day = new Day10(Input.getInput(2021,"day10"));
  }

  @Test
  void part1() {
    assertEquals(464991, day.part1());
  }

  @Test
  void part2() {
    assertEquals(3662008566L, day.part2());
  }
}