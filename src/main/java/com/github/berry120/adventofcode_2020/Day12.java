package com.github.berry120.adventofcode_2020;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day12 {

  private final int line1;
  private final String[] buses;

  public Day12(String input) {
    line1 = Integer.parseInt(input.split("\n")[0]);
    buses = input.split("\n")[1].split(",");
  }

  public int part1() {
    List<Integer> busNums = Arrays.stream(buses).filter(x -> !x.equals("x"))
        .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

    int minWait = Integer.MAX_VALUE;
    int bus = -1;

    for (int busNum : busNums) {
      int rem = busNum - (line1 % busNum);
      if (rem < minWait) {
        bus = busNum;
        minWait = rem;
      }
    }

    return bus * minWait;
  }

  public BigInteger part2() {

    BigInteger min = BigInteger.ZERO;
    BigInteger runningProduct = BigInteger.ONE;

    BigInteger i = BigInteger.ZERO;

    for (String str : buses) {
      if (!str.equals("x")) {
        BigInteger busNum = new BigInteger(str);

        while (!min.add(i).mod(busNum).equals(BigInteger.ZERO)) {
          min = min.add(runningProduct);
        }
        runningProduct = runningProduct.multiply(busNum);
      }
      i = i.add(BigInteger.ONE);
    }

    return min;
  }

}
