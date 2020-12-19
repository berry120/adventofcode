package com.github.berry120.adventofcode_2020;

import java.util.Map;
import java.util.stream.Collectors;

public class Day19 {

  private final String ruleInput;
  private final String exampleInput;

  public Day19(String input) {
    this.ruleInput = input.split("\n\n")[0];
    this.exampleInput = input.split("\n\n")[1];
  }

  public long part1() {
    return run(ruleInput);
  }

  public long part2() {
    return run(ruleInput.replace("8: 42",
        "8: 42 | 42 42 | 42 42 42 | 42 42 42 42 | 42 42 42 42 42 | 42 42 42 42 42 42 | 42 42 42 42 42 42 42 | 42 42 42 42 42 42 42 42 | 42 42 42 42 42 42 42 42 42")
        .replace("11: 42 31",
            "11: 42 31 | 42 42 31 31 | 42 42 42 31 31 31 | 42 42 42 42 31 31 31 31 | 42 42 42 42 42 31 31 31 31 31 | 42 42 42 42 42 42 31 31 31 31 31 31 | 42 42 42 42 42 42 42 31 31 31 31 31 31 31 | 42 42 42 42 42 42 42 42 31 31 31 31 31 31 31 31 | 42 42 42 42 42 42 42 42 42 31 31 31 31 31 31 31 31 31"));
  }

  public long run(String rules) {
    Map<Integer, String> rulesMap = rules.lines()
        .collect(Collectors.toMap(rule -> Integer.parseInt(rule.split(":")[0]),
            rule -> rule.split(":")[1].replace("\"", "").trim()));

    while (rulesMap.get(0).matches(".*\\d.*")) {
      rulesMap.put(0, simplify(rulesMap.get(0), rulesMap).trim().replaceAll("\\s+", " "));
    }

    return exampleInput.lines()
        .filter(x -> x.matches(rulesMap.get(0).replace(" ", "")))
        .count();
  }

  public String simplify(String val, Map<Integer, String> rules) {
    String[] parts = val.split(" ");
    String newRule = "";
    for (String str : parts) {
      boolean bracketEnd = false;
      if (str.startsWith("(")) {
        str = str.substring(1);
        newRule += "(";
      }
      if (str.endsWith(")")) {
        bracketEnd = true;
        str = str.substring(0, str.length() - 1);
      }

      if (str.matches("[ab]")) {
        newRule += str;
      } else {
        try {
          String replacement = rules.get(Integer.parseInt(str));
          if (replacement.contains("|")) {
            replacement = "(" + replacement + ")";
          }

          newRule += " " + replacement + " ";
        } catch (Exception ex) {
          newRule += str;
        }
      }
      if (bracketEnd) {
        newRule += ")";
      }
    }

    return newRule;
  }

}
