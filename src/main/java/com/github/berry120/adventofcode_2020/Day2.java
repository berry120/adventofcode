package com.github.berry120.adventofcode_2020;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
public class Day2 {

  public static final Pattern PATTERN = Pattern.compile("(\\d+)-(\\d+) ([a-z]): ([a-z]+)");

  private final String input;

  @Value
  static class Password {

    int firstNum;
    int secondNum;
    char letter;
    String text;

    public static Password fromLine(String line) {
      Matcher m = PATTERN.matcher(line);
      if (m.matches()) {
        return new Password(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)),
            m.group(3).charAt(0),
            m.group(4));
      }
      throw new AssertionError();
    }
  }

  public int part1() {
    int valid = 0;

    for (String line : input.split("\n")) {
      Password pw = Password.fromLine(line);

      int num =
          pw.getText().length() - pw.getText().replace(Character.toString(pw.getLetter()), "")
              .length();

      if (num >= pw.getFirstNum() && num <= pw.getSecondNum()) {
        valid++;
      }

    }
    return valid;
  }

  public int part2() {
    int valid = 0;

    for (String line : input.split("\n")) {
      Password pw = Password.fromLine(line);

      if ((pw.getText().charAt(pw.getFirstNum() - 1) == pw.getLetter()) ^ (
          pw.getText().charAt(pw.getSecondNum() - 1) == pw.getLetter())) {
        valid++;
      }

    }
    return valid;
  }

}
