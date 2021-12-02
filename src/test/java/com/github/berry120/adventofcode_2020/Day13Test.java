package com.github.berry120.adventofcode_2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.adventofcode_2020.input.Input;
import java.math.BigInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day13Test {

  private Day13 day13;

  @BeforeEach
  void setup() {
    day13 = new Day13(Input.getInput(2020,"day13"));
  }

  @Test
  void part1() {
    assertEquals(3865, day13.part1());
  }

  @Test
  void part2() {
    assertEquals(new BigInteger("415579909629976"), day13.part2());
  }
}