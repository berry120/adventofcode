package com.github.berry120.adventofcode_2020;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day18 {

  private final String input;
  private static final Map<Character, BiFunction<Long, Long, Long>> OPERATOR_MAP = Map
      .of('+', Math::addExact, '*', Math::multiplyExact);

  public long part1() {
    long sum = 0;
    for (String str : input.split("\n")) {
      long result = Long.parseLong(resolveBrackets(str, false));
      sum += result;
    }
    return sum;
  }

  public long part2() {
    long sum = 0;
    for (String str : input.split("\n")) {
      long result = Long.parseLong(resolveBrackets(str, true));
      sum += result;
    }
    return sum;
  }

  private static int nextOperatorIdx(String expression, boolean plusPrecedence) {
    if (plusPrecedence) {
      if (expression.contains("+")) {
        return expression.indexOf("+");
      } else {
        return expression.indexOf("*");
      }
    } else {
      return IntStream.of(expression.indexOf("+"), expression.indexOf("*"))
          .filter(x -> x > 0)
          .min().orElseThrow();
    }
  }

  private static String resolveBrackets(String expression, boolean plusPrecedence) {
    expression = expression.replace(" ", "");
    if (expression.contains("(")) {
      int endIdx = expression.indexOf(")");
      int startIdx = expression.substring(0, endIdx).lastIndexOf("(");

      String simplified =
          expression.substring(0, startIdx) + resolveBrackets(
              expression.substring(startIdx + 1, endIdx), plusPrecedence) + expression
              .substring(endIdx + 1);

      return resolveBrackets(simplified, plusPrecedence);
    } else if (expression.contains("+") || expression.contains("*")) {

      int firstOperatorIndex = nextOperatorIdx(expression, plusPrecedence);

      int startIdx = firstOperatorIndex - 1;
      while (startIdx > 0 && Character.isDigit(expression.charAt(startIdx - 1))) {
        startIdx--;
      }
      int endIdx = firstOperatorIndex + 1;
      while (endIdx < expression.length() - 1 && Character.isDigit(expression.charAt(endIdx + 1))) {
        endIdx++;
      }

      return resolveBrackets(expression.substring(0, startIdx) +
                             OPERATOR_MAP.get(expression.charAt(firstOperatorIndex))
                                 .apply(Long.parseLong(
                                     expression.substring(startIdx, firstOperatorIndex)),
                                     Long.parseLong(
                                         expression.substring(firstOperatorIndex + 1, endIdx + 1)))
                             + expression
                                 .substring(endIdx + 1), plusPrecedence);
    } else {
      return expression;
    }
  }

}
