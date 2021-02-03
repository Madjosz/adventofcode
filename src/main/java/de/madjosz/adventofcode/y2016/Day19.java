package de.madjosz.adventofcode.y2016;

import java.util.List;


public class Day19 {

    private final int elves;

    public Day19(List<String> input) {
        this.elves = Integer.parseInt(input.get(0));
    }

    public int a1() {
        // Josephus Survivor
        return ~Integer.highestOneBit(elves << 1) & ((elves << 1) | 1);
    }

    public int a2() {
        int counter = 0;
        int step = 1;
        for (int i = 1; i < elves; ++i) {
            counter += step;
            if (counter > i + 1) {
                counter = 1;
                step = 1;
            } else if (counter >= (i >> 1) + 1) step = 2;
        }
        return counter;
    }
}
