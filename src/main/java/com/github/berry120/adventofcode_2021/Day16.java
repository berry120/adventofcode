package com.github.berry120.adventofcode_2021;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Day16 {

  private final String input;

  public int part1() {
    String bin = hexToBin(input);

    Packet p = parseNextPacket(bin);

    System.out.println(p.sumVersion());

    return 0;
  }

  public Packet parseNextPacket(String bin) {
    System.out.println("Parsing: " + bin);

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
      int val = Integer.parseInt(bits, 2);
      System.out.println("Found literal: " + val);
      return new LiteralPacket(version, id, idx, val);
    } else {
      String lengthTypeId = bin.substring(6, 7);
      if (lengthTypeId.equals("0")) {
        int totalLength = Integer.parseInt(bin.substring(7, 22), 2);
        System.out.println(bin.substring(7, 22));
        System.out.println("Found Operator, Length: " + totalLength);
        int endIdx = 22 + totalLength;

        String nextBin = bin.substring(22, endIdx);
        List<Packet> nested = new ArrayList<>();
        while (!nextBin.isEmpty()) {
          Packet p = parseNextPacket(nextBin);
          nested.add(p);
          nextBin = nextBin.substring(p.binaryLength);
        }
        return new NestedPacket(version, id, totalLength, nested);
      } else {
        int numSubPackets = Integer.parseInt(bin.substring(7, 18), 2);
        System.out.println("Found Operator, Subpackets: " + numSubPackets);
        String nextBin = bin.substring(18);
        int totalLength = 0;

        List<Packet> nested = new ArrayList<>();
        for (int i = 0; i < numSubPackets; i++) {
          System.out.println("Subpacket " + (i+1));
          Packet p = parseNextPacket(nextBin);
          totalLength += p.binaryLength;
          nested.add(p);
          nextBin = nextBin.substring(p.binaryLength);
        }
        return new NestedPacket(version, id, totalLength, nested);
      }
    }
  }

  public int part2() {
    return 0;
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

    abstract int sumVersion();
  }

  @Data
  static class LiteralPacket extends Packet {
    int literalVal;

    public LiteralPacket(int version, int type, int length, int val) {
      this.packetVersion = version;
      this.packetType = type;
      this.binaryLength = length;
      this.literalVal = val;
    }

    @Override
    int sumVersion() {
      return packetVersion;
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
    int sumVersion() {
      return packetVersion + nested.stream().map(x -> x.sumVersion()).reduce(0, Integer::sum);
    }
  }
}
