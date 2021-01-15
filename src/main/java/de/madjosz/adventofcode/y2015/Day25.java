package de.madjosz.adventofcode.y2015;

import static java.lang.Integer.parseInt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day25 {

    private final int row;
    private final int col;

    private static final Pattern COORDS = Pattern.compile("row (\\d+), column (\\d+)");

    public Day25(String input) {
        Matcher m = COORDS.matcher(input);
        if (!m.find()) throw new IllegalArgumentException();
        this.row = parseInt(m.group(1));
        this.col = parseInt(m.group(2));
    }

    public long a1() {
        int n = getSequencePosition();
        long code = 20151125L;
        for (int i = 0; i < n; ++i) {
            code *= 252533L;
            code %= 33554393L;
        }
        return code;
    }

    public int getSequencePosition() {
        return (row + col - 2) * (row + col - 1) / 2 + col - 1;
    }

    public String a2() {
        return "50 stars";
    }
}
