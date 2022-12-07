package com.github.berry120.adventofcode_2022;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day7 {

  private final String input;

  public Directory buildTree() {
    boolean lsMode = false;

    List<Directory> dirList = new ArrayList<>();
    List<File> fileList = new ArrayList<>();

    Directory currentDir = null;

    for (String line : input.split("\n")) {
      if (line.startsWith("$")) {
        if (lsMode) {
          lsMode = false;

          currentDir.directories().addAll(dirList);
          currentDir.files().addAll(fileList);

          dirList = new ArrayList<>();
          fileList = new ArrayList<>();
        }
      } else {
        if (line.startsWith("dir")) {
          dirList.add(
              new Directory(line.substring(4), currentDir, new ArrayList<>(), new ArrayList<>()));
        } else {
          fileList.add(new File(Integer.parseInt(line.split(" ")[0])));
        }
      }

      if (line.startsWith("$ cd")) {
        String changeDir = line.substring(5);
        if (changeDir.equals("/")) {
          currentDir = new Directory("", null, new ArrayList<>(), new ArrayList<>());
        } else if (changeDir.equals("..")) {
          currentDir = currentDir.parent();
        } else {
          currentDir =
              currentDir.directories().stream()
                  .filter(d -> d.name().equals(changeDir))
                  .findAny()
                  .orElseThrow();
        }
      } else if (line.startsWith("$ ls")) {
        lsMode = true;
      }
    }
    if (lsMode) {
      currentDir.directories().addAll(dirList);
      currentDir.files().addAll(fileList);
    }

    while (currentDir.parent() != null) {
      currentDir = currentDir.parent();
    }
    return currentDir;
  }

  public int part1() {
    int sum = 0;
    Stack<Directory> s = new Stack<>();
    s.push(buildTree());
    while (!s.isEmpty()) {
      Directory d = s.pop();
      if (d.size() <= 100000) {
        sum += d.size();
      }
      d.directories().forEach(s::push);
    }
    return sum;
  }

  public int part2() {
    int x = Integer.MAX_VALUE;
    Stack<Directory> s = new Stack<>();
    s.push(buildTree());
    int minSize = 30000000 - (70000000 - s.peek().size());
    while (!s.isEmpty()) {
      Directory d = s.pop();
      if (d.size() >= minSize && d.size() < x) {
        x = d.size();
      }
      d.directories().forEach(s::push);
    }
    return x;
  }
}

record Directory(String name, Directory parent, List<Directory> directories, List<File> files) {

  public int size() {
    return files.stream().mapToInt(File::size).sum()
        + directories.stream().mapToInt(Directory::size).sum();
  }
}

record File(int size) {}
