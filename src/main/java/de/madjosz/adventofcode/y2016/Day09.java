package de.madjosz.adventofcode.y2016;

import java.util.List;


public class Day09 {

    private final List<String> input;

    public Day09(List<String> input) {
        this.input = input;
    }

    public int a1() {
        return input.stream().mapToInt(this::countMultiples).sum();
    }

    private record Multiplier(int length, int times, int selfSize) {}

    private int countMultiples(String line) {
        int lastpos = 0;
        int count = 0;
        while (true) {
            int pos = line.indexOf('(', lastpos);
            if (pos == -1) return count + line.length() - lastpos;
            count += pos - lastpos;
            Multiplier m = getMultiplier(line, pos);
            count += m.length() * m.times();
            lastpos = pos + m.length() + m.selfSize();
        }
    }

    private Multiplier getMultiplier(String line, int pos) {
        int close = line.indexOf(')', pos + 1);
        String[] parts = line.substring(pos + 1, close).split("x");
        return new Multiplier(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), close - pos + 1);
    }

    public long a2() {
        return input.stream().mapToLong(this::countDeepMultiples).sum();
    }

    private long countDeepMultiples(String line) {
        int lastpos = 0;
        long count = 0L;
        while (true) {
            int pos = line.indexOf('(', lastpos);
            if (pos == -1) return count + line.length() - lastpos;
            count += pos - lastpos;
            Multiplier m = getMultiplier(line, pos);
            count += countDeepMultiples(line.substring(pos + m.selfSize(), pos + m.selfSize() + m.length()))
                    * m.times();
            lastpos = pos + m.length() + m.selfSize();
        }
    }
}
