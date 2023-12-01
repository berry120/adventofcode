package com.github.berry120.adventofcode_2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day16Test {

  private Day16 day16;

  @BeforeEach
  void setup() {
    day16 = new Day16(Input.getInput(2020,"day16"));
  }

  @Test
  void part1() {
    System.out.println(day16.part1());
  }

  @Test
  void part2() {
    System.out.println(day16.part2());
  }
}