package de.madjosz.adventofcode.y2016;

import de.madjosz.adventofcode.data.MD5;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day14 {

    private final String salt;

    private final MessageDigest md5 = MD5.get();

    public Day14(List<String> input) {
        this.salt = input.get(0);
    }

    private static final Pattern three = Pattern.compile("(.)\\1\\1");
    private static final Pattern five = Pattern.compile("(.)\\1{4}");

    public int a1() {
        return findKey64(repeatedMD5(0));

    }

    private int findKey64(UnaryOperator<String> keyGenerator) {
        List<Integer> keys = new ArrayList<>();
        Map<String, List<Integer>> found = new HashMap<>();
        for (int i = 0; i < 16; ++i)
            found.put(Integer.toString(i, 16), new ArrayList<>());

        for (int i = 0; i < Integer.MAX_VALUE; ++i) {
            String hash = keyGenerator.apply(salt + i);
            Matcher m = three.matcher(hash);
            if (m.find()) {
                found.get(m.group(1)).add(i);
                checkFive(found, i, hash, keys);
            }
            if (keys.size() >= 64 && keys.get(63) + 1000 < i) return keys.get(63);
        }
        throw new IllegalStateException();
    }

    private void checkFive(Map<String, List<Integer>> found, int i, String hash, List<Integer> keys) {
        Matcher m = five.matcher(hash);
        while (m.find()) {
            List<Integer> positions = found.get(m.group(1));
            positions.removeIf(moreThan1000Ago(i));
            for (Integer k : positions)
                if (k != i) {
                    int idx = Collections.binarySearch(keys, k);
                    if (idx < 0) keys.add(~idx, k);
                }
        }
    }

    private static Predicate<Integer> moreThan1000Ago(int i) {
        return k -> k + 1000 < i;
    }

    public int a2() {
        return findKey64(repeatedMD5(2016));
    }

    UnaryOperator<String> repeatedMD5(int repeats) {
        return s -> {
            byte[] hash = format(md5.digest(s.getBytes()));
            for (int i = 0; i < repeats; ++i)
                hash = format(md5.digest(hash));
            return new String(hash);
        };
    }

    private static final byte[] CHARS = new byte[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c',
            'd', 'e', 'f' };

    private static byte[] format(byte[] bytes) {
        byte[] formatted = new byte[2 * bytes.length];
        for (int i = 0; i < bytes.length; ++i) {
            formatted[i << 1] = CHARS[(bytes[i] & 0xF0) >> 4];
            formatted[(i << 1) + 1] = CHARS[bytes[i] & 0xF];
        }
        return formatted;
    }
}
