package de.madjosz.adventofcode.y2016;

import java.util.BitSet;
import java.util.List;


public class Day16 {

    private final String input;

    public Day16(List<String> input) {
        this.input = input.get(0);
    }

    private record DragonCurve(BitSet bits, int length) {}

    public String a1() {
        return a1(272);
    }

    String a1(int diskSize) {
        DragonCurve dragon = foldUntil(diskSize, parseInput());
        return checkSum(dragon.bits(), diskSize);
    }

    private DragonCurve parseInput() {
        BitSet bits = new BitSet(input.length());
        for (int i = 0; i < input.length(); ++i)
            if (input.charAt(i) == '1') bits.set(i);
        return new DragonCurve(bits, input.length());
    }

    private DragonCurve foldUntil(int diskSize, DragonCurve dragon) {
        int length = dragon.length();
        BitSet newCurve = (BitSet) dragon.bits().clone();
        while (length < diskSize) {
            for (int i = 0; i < length; ++i)
                newCurve.set(i + length + 1, !newCurve.get(length + ~i));
            length = 2 * length + 1;
        }
        return new DragonCurve(newCurve, length);
    }

    private String checkSum(BitSet bits, int diskSize) {
        int halfSize = diskSize >> 1;
        BitSet checkSum = new BitSet(halfSize);
        for (int i = 0; i < halfSize; ++i)
            checkSum.set(i, bits.get(i << 1) == bits.get((i << 1) + 1));
        if (halfSize % 2 == 0) return checkSum(checkSum, halfSize);
        return format(checkSum, halfSize);
    }

    private String format(BitSet checkSum, int length) {
        StringBuilder b = new StringBuilder(length);
        for (int i = 0; i < length; ++i)
            b.append(checkSum.get(i) ? '1' : '0');
        return b.toString();
    }

    public String a2() {
        return a1(35651584);
    }
}
