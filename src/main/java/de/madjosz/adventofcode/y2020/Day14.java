package de.madjosz.adventofcode.y2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;


public class Day14 {

    private final List<String> program;

    public Day14(List<String> input) {
        this.program = input;
    }

    private static Pattern p = Pattern.compile("mem\\[(\\d+)\\] = (\\d+)");

    public long a1() {
        long[] mem = new long[0xFFFF];
        Mask mask = new Mask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        for (String line : program) {
            if (line.startsWith("mask = ")) mask = new Mask(line.substring(7));
            else {
                Matcher m = p.matcher(line);
                if (!m.find()) throw new IllegalArgumentException(line);
                int idx = Integer.parseInt(m.group(1));
                long value = Long.parseLong(m.group(2));
                mem[idx] = mask.apply(value);
            }
        }
        return Arrays.stream(mem).sum();
    }

    private static class Mask {

        private final long and;
        private final long or;

        public Mask(String mask) {
            this.and = Long.parseLong(mask.replaceAll("[^0]", "1"), 2);
            this.or = Long.parseLong(mask.replaceAll("[^1]", "0"), 2);
        }

        public long apply(long input) {
            return (input & and) | or;
        }
    }

    public long a2() {
        Map<Long, Long> mem = new HashMap<>();
        Mask2 mask = new Mask2("000000000000000000000000000000000000");
        for (String line : program) {
            if (line.startsWith("mask = ")) mask = new Mask2(line.substring(7));
            else {
                Matcher m = p.matcher(line);
                if (!m.find()) throw new IllegalArgumentException(line);
                int idx = Integer.parseInt(m.group(1));
                long value = Long.parseLong(m.group(2));
                for (Long address : mask.apply(idx))
                    mem.put(address, value);
            }
        }
        return mem.values().stream().mapToLong(l -> l).sum();
    }

    private static class Mask2 {

        private final int[] x;
        private final long or;

        public Mask2(String mask) {
            int length = mask.length();
            this.x = IntStream.range(0, length).filter(i -> mask.charAt(length - i - 1) == 'X').toArray();
            this.or = Long.parseLong(mask.replaceAll("[^1]", "0"), 2);
        }

        public List<Long> apply(long input) {
            List<Long> results = new ArrayList<>();
            results.add(input | or);
            for (int i = 0; i < x.length; ++i) {
                List<Long> newResults = new ArrayList<>();
                for (Long l : results) {
                    long mask = 1L << x[i];
                    newResults.add(l | mask);
                    newResults.add(l & ~mask);
                }
                results = newResults;
            }
            return results;
        }
    }

}
