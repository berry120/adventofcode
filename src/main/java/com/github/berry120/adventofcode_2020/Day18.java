package com.github.berry120.adventofcode_2020;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day18 {

  private final String input;

  public long part1() {
    long sum = 0;
    for (String str : input.split("\n")) {
      long result = Long.parseLong(resolveBrackets(str));
      sum += result;
    }
    return sum;
  }

  private static String eval(String expression) {
    if (expression.contains("+") || expression.contains("*")) {

      int firstOperatorIndex = expression.indexOf("+");
      if (firstOperatorIndex == -1 || (expression.indexOf("*") != -1
                                       && expression.indexOf("*") < firstOperatorIndex)) {
        firstOperatorIndex = expression.indexOf("*");
      }

      int secondOperatorIndex = expression.indexOf("+", firstOperatorIndex + 1);
      if (secondOperatorIndex == -1 || (expression.indexOf("*", firstOperatorIndex + 1) != -1
                                        && expression.indexOf("*", firstOperatorIndex + 1)
                                           < secondOperatorIndex)) {
        secondOperatorIndex = expression.indexOf("*", firstOperatorIndex + 1);
      }

      if (secondOperatorIndex == -1) {
        secondOperatorIndex = expression.length();
      }

      String part = expression.substring(0, secondOperatorIndex);
      String[] parts = part.split("[+*]");

      long result;
      if (part.contains("+")) {
        result = Long.parseLong(parts[0]) + Long.parseLong(parts[1]);
      } else if (part.contains("*")) {
        result = Long.parseLong(parts[0]) * Long.parseLong(parts[1]);
      } else {
        throw new AssertionError(expression);
      }

      String ret = eval(result + expression.substring(secondOperatorIndex));

      return ret;

    } else {
      return expression;
    }
  }

  private static String resolveBrackets(String expression) {
    expression = expression.replace(" ", "");
    if (expression.contains("(")) {
      int endIdx = expression.indexOf(")");
      int startIdx = expression.substring(0, endIdx).lastIndexOf("(");

      String toResolve = expression.substring(startIdx + 1, endIdx);
      String simplified =
          expression.substring(0, startIdx) + resolveBrackets(toResolve) + expression
              .substring(endIdx + 1);

      return resolveBrackets(simplified);
    } else {
      return eval(expression);
    }
  }

  public int part2() {
    return 0;
  }

}
