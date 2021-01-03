package de.madjosz.adventofcode.y2020;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


public class Day13 {

    private final List<String> notes;
    private final int[] busses;
    private final int[] busidx;

    public Day13(List<String> input) {
        this.notes = input;
        busses = Arrays.stream(notes.get(1).split(","))
                .filter(b -> !"x".equals(b))
                .mapToInt(Integer::parseInt)
                .toArray();
        String[] busEntries = notes.get(1).split(",");
        busidx = IntStream.range(0, busEntries.length).filter(i -> !"x".equals(busEntries[i])).toArray();
    }

    public int a1() {
        int time = Integer.parseInt(notes.get(0));
        int[] min = new int[] { Integer.MAX_VALUE, 0 };
        for (int bus : busses) {
            int delay = bus - (time % bus);
            if (delay < min[0]) {
                min[0] = delay;
                min[1] = bus;
            }
        }
        return min[0] * min[1];
    }

    public long a2() {
        long step = busses[0];
        long timestamp = getDelay(busses[0], busidx[0]);
        for (int i = 1; i < busses.length; ++i) {
            long bus = busses[i];
            long delay = getDelay(bus, busidx[i]);
            while (timestamp % bus != delay)
                timestamp += step;
            step *= bus;
        }
        return timestamp;
    }

    private static long getDelay(long bus, int busidx) {
        long delay = (bus - busidx) % bus;
        if (delay < 0) return delay + bus;
        return delay;
    }

    // _0, _9, _19, 27, 36, 48, _50, 56, 63
    // 19, 41, 823, 23, 17, 29, 443, 37, 13
    // 823 = 412
    // 886 % 823 = 63

    public long _a2() {
        long start = getStart();
        long step = 823L * 443L;
        for (long i = start; i < Long.MAX_VALUE - step; i += step)
            if (checkTimestamp(i)) return i - 19;
        return 0;
    }

    private long getStart() {
        long i = 0;
        while (i % 443 != 412)
            i += 823;
        return i;
    }

    private boolean checkTimestamp(long timestamp) {
        for (int j = 0; j < busidx.length; ++j)
            if ((timestamp + busidx[j] - 19) % busses[j] != 0) return false;
        return true;
    }

}
