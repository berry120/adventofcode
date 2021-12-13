package com.github.berry120.adventofcode_2021;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day13Test {

  private Day13 day;

  @BeforeEach
  void setup() {
    day = new Day13(Input.getInput(2021, "day13"));
  }

  @Test
  void part1() {
    assertEquals(701, day.part1());
  }

  @Test
  void part2() {
    assertEquals(
        "#### ###  #### #  # ###  ####   ## #   \n"
            + "#    #  # #    # #  #  # #       # #   \n"
            + "###  #  # ###  ##   ###  ###     # #   \n"
            + "#    ###  #    # #  #  # #       # #   \n"
            + "#    #    #    # #  #  # #    #  # #   \n"
            + "#    #    #### #  # ###  ####  ##  ####",
        day.part2());
  }
}
