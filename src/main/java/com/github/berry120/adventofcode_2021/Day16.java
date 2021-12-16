package com.github.berry120.adventofcode_2021;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Day16 {

  private final String input;

  public long part1() {
    return parseNextPacket(hexToBin(input)).sumVersion();
  }

  public Packet parseNextPacket(String bin) {
    int version = Integer.parseInt(bin.substring(0, 3), 2);
    int id = Integer.parseInt(bin.substring(3, 6), 2);
    if (id == 4) {
      String bits = "";
      int idx = 6;
      while (true) {
        String substr = bin.substring(idx, idx + 5);
        bits += substr.substring(1);
        idx += 5;
        if (substr.startsWith("0")) {
          break;
        }
      }
      long val = Long.parseLong(bits, 2);
      System.out.println("Found literal: " + val);
      return new LiteralPacket(version, id, idx, val);
    } else {
      String lengthTypeId = bin.substring(6, 7);
      if (lengthTypeId.equals("0")) {
        int totalLength = Integer.parseInt(bin.substring(7, 22), 2);
        System.out.println("Found Operator, Length: " + totalLength);
        int endIdx = 22 + totalLength;

        String nextBin = bin.substring(22, endIdx);
        List<Packet> nested = new ArrayList<>();
        while (!nextBin.isEmpty()) {
          Packet p = parseNextPacket(nextBin);
          nested.add(p);
          nextBin = nextBin.substring(p.binaryLength);
        }
        return new NestedPacket(version, id, endIdx, nested);
      } else {
        long numSubPackets = Long.parseLong(bin.substring(7, 18), 2);
        System.out.println("Found Operator, Subpackets: " + numSubPackets);
        String nextBin = bin.substring(18);
        int totalLength = 18;

        List<Packet> nested = new ArrayList<>();
        for (long i = 0; i < numSubPackets; i++) {
          System.out.println("Subpacket " + (i+1));
          Packet p = parseNextPacket(nextBin);
          totalLength += p.binaryLength;
          nested.add(p);
          nextBin = nextBin.substring(p.binaryLength);
        }
        System.out.println("Length was: " + totalLength);
        System.out.println("Returning nested packet of length " + nested.size());
        return new NestedPacket(version, id, totalLength, nested);
      }
    }
  }

  public long part2() {
    return parseNextPacket(hexToBin(input)).eval();
  }

  private String hexToBin(String hex) {
    hex = hex.replaceAll("0", "0000");
    hex = hex.replaceAll("1", "0001");
    hex = hex.replaceAll("2", "0010");
    hex = hex.replaceAll("3", "0011");
    hex = hex.replaceAll("4", "0100");
    hex = hex.replaceAll("5", "0101");
    hex = hex.replaceAll("6", "0110");
    hex = hex.replaceAll("7", "0111");
    hex = hex.replaceAll("8", "1000");
    hex = hex.replaceAll("9", "1001");
    hex = hex.replaceAll("A", "1010");
    hex = hex.replaceAll("B", "1011");
    hex = hex.replaceAll("C", "1100");
    hex = hex.replaceAll("D", "1101");
    hex = hex.replaceAll("E", "1110");
    hex = hex.replaceAll("F", "1111");
    return hex;
  }

  @Data
  abstract static class Packet {
    int packetVersion;
    int packetType;
    int binaryLength;

    abstract long sumVersion();
    abstract long eval();
  }

  @Data
  static class LiteralPacket extends Packet {
    long literalVal;

    public LiteralPacket(int version, int type, int length, long val) {
      this.packetVersion = version;
      this.packetType = type;
      this.binaryLength = length;
      this.literalVal = val;
    }

    @Override
    long sumVersion() {
      return packetVersion;
    }

    long eval() {
      return literalVal;
    }
  }

  @Data
  static class NestedPacket extends Packet {
    List<Packet> nested;

    public NestedPacket(int version, int type, int length, List<Packet> nested) {
      this.packetVersion = version;
      this.packetType = type;
      this.binaryLength = length;
      this.nested = nested;
    }

    @Override
    long sumVersion() {
      return packetVersion + nested.stream().map(x -> x.sumVersion()).reduce(0L, Long::sum);
    }

    long eval() {
      switch(packetType) {
        case 0 -> {
          return nested.stream().map(x->x.eval()).reduce(0L,Long::sum);
        }
        case 1 -> {
          return nested.stream().map(x->x.eval()).reduce(1L,(a,b)->a*b);
        }
        case 2 -> {
          return nested.stream().map(x->x.eval()).reduce(Long.MAX_VALUE,Long::min);
        }
        case 3 -> {
          return nested.stream().map(x->x.eval()).reduce(0L,Long::max);
        }
        case 5 -> {
          return nested.get(0).eval()>nested.get(1).eval()?1:0;
        }
        case 6 -> {
          return nested.get(0).eval()<nested.get(1).eval()?1:0;
        }
        case 7 -> {
          return nested.get(0).eval()==nested.get(1).eval()?1:0;
        }
        default -> {
          throw new RuntimeException();
        }
      }
    }
  }
}
