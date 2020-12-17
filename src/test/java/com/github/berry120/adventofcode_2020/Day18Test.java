package com.github.berry120.adventofcode_2020;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day18Test {

  private Day18 day18;

  @BeforeEach
  void setup() {
    day18 = new Day18(Input.getInput("day18"));
  }

  @Test
  void part1() {
    System.out.println(day18.part1());
  }

  @Test
  void part2() {
    System.out.println(day18.part2());
  }
}