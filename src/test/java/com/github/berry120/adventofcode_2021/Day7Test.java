package com.github.berry120.adventofcode_2021;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day7Test {

  private Day7 day7;

  @BeforeEach
  void setup() {
    day7 = new Day7(Input.getInput(2021, "day7"));
  }

  @Test
  void part1() {
    assertEquals(343441, day7.part1());
  }

  @Test
  void part2() {
    assertEquals(98925151, day7.part2());
  }
}
