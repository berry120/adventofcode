package com.github.berry120.adventofcode_2024;

import java.util.regex.Pattern;
import java.util.stream.Gatherers;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day3 {

  private String input;

  public long part1() {
    return Pattern.compile("mul\\((\\d+),(\\d+)\\)")
        .matcher(input)
        .results()
        .map(mr -> Integer.parseInt(mr.group(1)) * Integer.parseInt(mr.group(2)))
        .reduce(Integer::sum)
        .orElse(0);
  }

  public long part2() {
    return Pattern.compile("((do\\(\\))|(don't\\(\\))|mul\\((\\d+),(\\d+)\\))")
        .matcher(input)
        .results()
        .gather(
            Gatherers.fold(
                () -> "0",
                (str, m) ->
                    m.group(2) != null
                        ? str.replace("_", "")
                        : (m.group(3) != null
                            ? str + "_"
                            : (m.group(4) != null && !str.endsWith("_")
                                ? Integer.toString(
                                    (Integer.parseInt(str)
                                        + Integer.parseInt(m.group(4))
                                            * Integer.parseInt(m.group(5))))
                                : str))))
        .findFirst()
        .map(s -> Integer.parseInt(s.replace("_", "")))
        .orElse(0);
  }
}
