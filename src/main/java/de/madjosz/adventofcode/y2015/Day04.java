package de.madjosz.adventofcode.y2015;

import de.madjosz.adventofcode.data.MD5;
import java.security.MessageDigest;
import java.util.function.Predicate;


public class Day04 {

    private final String secretKey;

    private static final MessageDigest md5 = MD5.get();

    public Day04(String input) {
        this.secretKey = input;
    }

    public int a1() {
        return findSecret(Day04::fiveZeros);
    }

    private int findSecret(Predicate<byte[]> checkDigest) {
        for (int i = 0;; ++i) {
            String key = secretKey + i;
            byte[] digest = md5.digest(key.getBytes());
            if (checkDigest.test(digest)) return i;
        }
    }

    private static boolean fiveZeros(byte[] digest) {
        return digest[0] == 0 && digest[1] == 0 && digest[2] >= 0 && digest[2] < 16;
    }

    public int a2() {
        return findSecret(Day04::sixZeros);
    }

    private static boolean sixZeros(byte[] digest) {
        return digest[0] == 0 && digest[1] == 0 && digest[2] == 0;
    }
}
