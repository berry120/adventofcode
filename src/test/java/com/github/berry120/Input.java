package com.github.berry120;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Input {

  public static String getInput(int year, String day) {
    InputStream is =
        Input.class.getClassLoader().getResourceAsStream("input/" + year + "/" + day + ".input");

    return new BufferedReader(
            new InputStreamReader(Objects.requireNonNull(is), StandardCharsets.UTF_8))
        .lines()
        .collect(Collectors.joining("\n"));
  }
}
