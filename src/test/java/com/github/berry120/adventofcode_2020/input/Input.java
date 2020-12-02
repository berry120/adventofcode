package com.github.berry120.adventofcode_2020.input;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Input {

  public static String getInput(String day) {
    InputStream is = Input.class.getClassLoader().getResourceAsStream(day + ".input");

    return new BufferedReader(
        new InputStreamReader(Objects.requireNonNull(is), StandardCharsets.UTF_8))
        .lines()
        .collect(Collectors.joining("\n"));
  }

}
