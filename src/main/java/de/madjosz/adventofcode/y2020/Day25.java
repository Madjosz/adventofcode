package de.madjosz.adventofcode.y2020;

import java.util.List;


public class Day25 {

    private final long[] publicKeys;

    public Day25(List<String> input) {
        this.publicKeys = input.stream().mapToLong(Long::parseLong).toArray();
    }

    public long a1() {
        return a1(0);
    }

    public long a1(int keyindex) {
        int cardloop = getLoopsize(publicKeys[(1 + keyindex) % 2]);
        long encryptionKey = 1;
        for (int i = 0; i < cardloop; ++i)
            encryptionKey = transform(encryptionKey, publicKeys[keyindex % 2]);
        return encryptionKey;
    }

    private static int getLoopsize(long key) {
        int loopsize = 0;
        long number = 1L;
        while (number != key) {
            number = transform(number, 7);
            ++loopsize;
        }
        return loopsize;
    }

    private static final long MODULUS = 20201227L;

    private static long transform(long number, long subject) {
        number *= subject;
        number %= MODULUS;
        return number;
    }

    public String a2() {
        return "we are done";
    }

}
