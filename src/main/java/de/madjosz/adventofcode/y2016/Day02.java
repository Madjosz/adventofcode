package de.madjosz.adventofcode.y2016;

import static java.lang.Math.abs;

import java.util.List;
import java.util.function.IntConsumer;


public class Day02 {

    private final List<String> input;

    public Day02(List<String> input) {
        this.input = input;
    }

    public int a1() {
        int[] pos = new int[] { 1, 1 };
        int code = 0;
        for (String line : input) {
            line.chars().forEach(move1(pos));
            code = code * 10 + 3 * pos[1] + pos[0] + 1;
        }
        return code;
    }

    private IntConsumer move1(int[] pos) {
        return i -> {
            switch (i) {
            case 'D': if (pos[1] < 2) ++pos[1]; break;
            case 'U': if (pos[1] > 0) --pos[1]; break;
            case 'R': if (pos[0] < 2) ++pos[0]; break;
            case 'L': if (pos[0] > 0) --pos[0]; break;
            }
        };
    }

    private static final char[] KEYPAD = new char[] { '1', '3', '7', 'B', 'D' };

    public String a2() {
        int[] pos = new int[] { -2, 0 };
        StringBuilder code = new StringBuilder();
        for (String line : input) {
            line.chars().forEach(move2(pos));
            code.appendCodePoint(KEYPAD[pos[1] + 2] + pos[0]);
        }
        return code.toString();
    }

    private IntConsumer move2(int[] pos) {
        return i -> {
            switch (i) {
            case 'D': if (pos[1] + abs(pos[0]) <  2) ++pos[1]; break;
            case 'U': if (pos[1] - abs(pos[0]) > -2) --pos[1]; break;
            case 'R': if (pos[0] + abs(pos[1]) <  2) ++pos[0]; break;
            case 'L': if (pos[0] - abs(pos[1]) > -2) --pos[0]; break;
            }
        };
    }
}
