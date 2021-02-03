package de.madjosz.adventofcode.y2016;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;


public class Day20 {

    private final List<long[]> blacklist;

    public Day20(List<String> input) {
        this.blacklist = input.stream()
                .map(s -> s.split("-"))
                .map(r -> new long[] { Long.parseLong(r[0]), Long.parseLong(r[1]) })
                .collect(toList());
    }

    public long a1() {
        Collections.sort(blacklist, comparingLong(r -> r[0]));
        long lowest = blacklist.get(0)[0];
        if (lowest > 0L) return 0L;
        long highest = blacklist.get(0)[1];
        for (long[] range : blacklist) {
            if (range[0] > highest + 1) return highest + 1;
            highest = Math.max(highest, range[1]);
        }
        return highest + 1;
    }

    public long a2() {
        Collections.sort(blacklist, comparingLong(r -> r[0]));
        long allowed = blacklist.get(0)[0];
        long highest = blacklist.get(0)[1];
        for (long[] range : blacklist) {
            if (range[0] > highest + 1) allowed += range[0] - highest - 1;
            highest = Math.max(highest, range[1]);
        }
        return allowed + (1L << 32) - highest - 1;
    }
}
