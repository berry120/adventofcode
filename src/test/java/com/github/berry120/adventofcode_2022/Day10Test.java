package com.github.berry120.adventofcode_2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.berry120.adventofcode_2020.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day10Test {

  private Day10 day;

  @BeforeEach
  void setup() {
    day = new Day10(Input.getInput(2022,"day10"));
  }

  @Test
  void part1() {
    assertEquals(13740, day.part1());
  }

  @Test
  void part2() {
    assertEquals("""
        ####.#..#.###..###..####.####..##..#....
        ...#.#..#.#..#.#..#.#....#....#..#.#....
        ..#..#..#.#..#.#..#.###..###..#....#....
        .#...#..#.###..###..#....#....#....#....
        #....#..#.#....#.#..#....#....#..#.#....
        ####..##..#....#..#.#....####..##..####.
        """, day.part2());
  }
}