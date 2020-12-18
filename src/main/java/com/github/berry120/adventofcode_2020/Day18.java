package com.github.berry120.adventofcode_2020;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day18 {

  private final String input;
  private static final Map<Character, BiFunction<Long, Long, Long>> OPERATOR_MAP = Map
      .of('+', Math::addExact, '*', Math::multiplyExact);

  public long part1() {
    return sum(false);
  }

  public long part2() {
    return sum(true);
  }

  public long sum(boolean plusPrecedence) {
    return input.lines()
        .mapToLong(str -> Long.parseLong(eval(str.replace(" ", ""), plusPrecedence)))
        .sum();
  }

  private static int nextOperatorIdx(String expression, boolean plusPrecedence) {
    return plusPrecedence ?
        Optional.of(expression.indexOf("+"))
            .filter(x -> x > 0)
            .orElse(expression.indexOf("*")) :
        IntStream.of(expression.indexOf("+"), expression.indexOf("*"))
            .filter(x -> x > 0)
            .min().orElse(expression.length());
  }

  private static String eval(String expression, boolean plusPrecedence) {
    if (expression.contains("(")) {
      int endIdx = expression.indexOf(")");
      int startIdx = expression.substring(0, endIdx).lastIndexOf("(");

      return eval(expression.substring(0, startIdx) + eval(
          expression.substring(startIdx + 1, endIdx), plusPrecedence) + expression
                      .substring(endIdx + 1), plusPrecedence);
    } else if (expression.contains("+") || expression.contains("*")) {

      int firstOperatorIndex = nextOperatorIdx(expression, plusPrecedence);

      int startIdx =
          firstOperatorIndex - nextOperatorIdx(new StringBuilder(expression).reverse()
              .substring(expression.length() - firstOperatorIndex), false);
      int endIdx =
          firstOperatorIndex + nextOperatorIdx(expression.substring(firstOperatorIndex + 1), false)
          + 1;

      return eval(expression.substring(0, startIdx) +
                  OPERATOR_MAP.get(expression.charAt(firstOperatorIndex))
                      .apply(Long.parseLong(
                          expression.substring(startIdx, firstOperatorIndex)),
                          Long.parseLong(
                              expression.substring(firstOperatorIndex + 1, endIdx)))
                  + expression
                      .substring(endIdx), plusPrecedence);
    } else {
      return expression;
    }
  }

}
