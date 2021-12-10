package com.github.berry120.adventofcode_2021;

import com.sun.jdi.CharType;
import lombok.AllArgsConstructor;
import lombok.With;
import reactor.core.publisher.Flux;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class Day10 {

  private final String input;

  public int part1() {
    int sum=0;
    for(String line : input.split("\n")) {
      sum += isValid(line);
    }
    return sum;
  }

  public int isValid(String line) {
    boolean isLastOpen = false;
    Map<Integer, Character> indentLevels = new HashMap<>();
    int indentCounter = 0;
    for (int i = 0; i < line.length(); i++) {
      char currentChar = line.charAt(i);
      boolean isOpen = isOpen(currentChar);
      if (isOpen && isLastOpen) {
        indentCounter++;
      } else if (!isOpen && !isLastOpen) {
        indentCounter--;
      }
      isLastOpen = isOpen;

      Character c = indentLevels.get(indentCounter);
      if(c==null) {
        indentLevels.put(indentCounter, currentChar);
      }
      else {
        indentLevels.put(indentCounter, null);
        char expectedClose = getExpectedClose(c);
        if(expectedClose!=currentChar) {
          return getScore(currentChar);
        }
      }
    }
    return 0;
  }

  public long part2() {
    List<Long> scores = new ArrayList<>();
    for(String line : input.split("\n")) {
      if(isValid(line)==0) {
        scores.add(expectedClose(line));
      }
    }
    Collections.sort(scores);
    return scores.get(scores.size() / 2);
  }

  public long expectedClose(String line) {
    boolean isLastOpen = false;
    Map<Integer, Character> indentLevels = new HashMap<>();
    int indentCounter = 0;
    for (int i = 0; i < line.length(); i++) {
      char currentChar = line.charAt(i);
      boolean isOpen = isOpen(currentChar);
      if (isOpen && isLastOpen) {
        indentCounter++;
      } else if (!isOpen && !isLastOpen) {
        indentCounter--;
      }
      isLastOpen = isOpen;

      Character c = indentLevels.get(indentCounter);
      if(c==null) {
        indentLevels.put(indentCounter, currentChar);
      }
      else {
        indentLevels.put(indentCounter, null);
      }
    }

    long score =0;
    for(int i=indentCounter ;i>=0 ; i--) {
      Character c = indentLevels.get(i);
      if(c==null) continue;
      c = getExpectedClose(c);
      score*=5;
      score+=getScore2(c);
    }
    return score;
  }

  long getScore2(char c) {
    if(c==')') return 1;
    if(c==']') return 2;
    if(c=='}') return 3;
    if(c=='>') return 4;
    throw new RuntimeException();
  }

  int getScore(char c) {
   if(c==')') return 3;
   if(c==']') return 57;
   if(c=='}') return 1197;
   if(c=='>') return 25137;
   throw new RuntimeException();
  }

  char getExpectedClose(char c) {
    if(c=='(') return ')';
    if(c=='<') return '>';
    if(c=='{') return '}';
    if(c=='[') return ']';
    throw new RuntimeException();
  }

  public boolean isOpen(char c) {
    return c == '(' || c == '[' || c == '{' || c == '<';
  }

  record State(char c, int level) {}
}
