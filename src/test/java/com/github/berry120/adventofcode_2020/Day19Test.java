package com.github.berry120.adventofcode_2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day19Test {

  private Day19 day19;

  @BeforeEach
  void setup() {
    day19 = new Day19(Input.getInput("day19"));
  }

  @Test
  void part1() {
    System.out.println(day19.part1());
  }

  @Test
  void part2() {
    System.out.println(day19.part2());
  }
}