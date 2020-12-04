package com.github.berry120.adventofcode_2020;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Day4 {

  private final String[] parts;

  public Day4(String input) {
    parts = input.split("\n\n");
  }

  public long part1() {
    return Arrays.stream(parts)
        .filter(
            part -> hasAll(part, "byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid:"))
        .count();
  }

  public long part2() {
    return Arrays.stream(parts)
        .filter(
            part -> hasAll(part, "byr:(19[2-8]\\d|199\\d|200[0-2])(\\s|$)",
                "iyr:(201\\d|2020)(\\s|$)",
                "eyr:(202\\d|2030)(\\s|$)",
                "hgt:((1[5-8]\\d|19[0-3])cm|(59|6\\d|7[0-6])in)(\\s|$)",
                "hcl:(#[\\da-f]{6})(\\s|$)",
                "ecl:(amb|blu|brn|gry|grn|hzl|oth)(\\s|$)",
                "pid:(\\d{9})(\\s|$)"))
        .count();
  }

  private boolean hasAll(String line, String... regexs) {
    return Arrays.stream(regexs)
        .allMatch(regex -> Pattern.compile(regex).matcher(line).find());
  }

}
