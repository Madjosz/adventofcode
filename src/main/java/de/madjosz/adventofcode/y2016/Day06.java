package de.madjosz.adventofcode.y2016;

import java.util.List;


public class Day06 {

    private final List<String> input;

    public Day06(List<String> input) {
        this.input = input;
    }

    public String a1() {
        int[][] modus = countOccurences();
        return findMost(modus);
    }

    private String findMost(int[][] modus) {
        StringBuilder msg = new StringBuilder(modus.length);
        for (int[] col : modus) {
            int max = col[0];
            int idx = 0;
            for (int i = 1; i < col.length; ++i) {
                if (col[i] > max) {
                    max = col[i];
                    idx = i;
                }
            }
            msg.appendCodePoint(idx + 'a');
        }
        return msg.toString();
    }

    private int[][] countOccurences() {
        int[][] modus = new int[input.get(0).length()][26];
        for (String line : input)
            for (int i = 0; i < line.length(); ++i)
                ++modus[i][line.charAt(i) - 'a'];
        return modus;
    }

    public String a2() {
        int[][] modus = countOccurences();
        return findLeast(modus);
    }

    private String findLeast(int[][] modus) {
        StringBuilder msg = new StringBuilder(modus.length);
        for (int[] col : modus) {
            int min = Integer.MAX_VALUE;
            int idx = -1;
            for (int i = 0; i < col.length; ++i) {
                if (col[i] > 0 && col[i] < min) {
                    min = col[i];
                    idx = i;
                }
            }
            msg.appendCodePoint(idx + 'a');
        }
        return msg.toString();
    }
}
