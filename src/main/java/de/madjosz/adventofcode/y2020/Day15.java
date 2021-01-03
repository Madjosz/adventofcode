package de.madjosz.adventofcode.y2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Day15 {

    private final int[] startingNumbers;

    public Day15(String input) {
        this.startingNumbers = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
    }

    public int a1() {
        return getNumber(2020);
    }

    private int getNumber(int n) {
        Map<Integer, Integer> occurences = new HashMap<>();
        for (int i = 0; i < startingNumbers.length - 1; ++i)
            occurences.put(startingNumbers[i], i);

        int last = startingNumbers[startingNumbers.length - 1];
        for (int i = startingNumbers.length; i < n; ++i) {
            Integer pos = occurences.get(last);
            occurences.put(last, i - 1);
            if (pos == null) last = 0;
            else last = i - 1 - pos;
        }
        return last;
    }

    public int a2() {
        return getNumber(30000000);
    }

}
