package de.madjosz.adventofcode.y2020;

import static java.lang.Integer.parseInt;

import java.util.List;
import java.util.regex.Pattern;


public class Day02 {

    private final List<String> input;

    public Day02(List<String> input) {
        this.input = input;
    }

    private static final Pattern p = Pattern.compile("^(\\d+)-(\\d+) (\\w): (\\w+)$");

    public long a1() {
        return input.stream().map(p::matcher).filter(m -> {
            if (!m.find()) throw new IllegalArgumentException();
            int x = parseInt(m.group(1));
            int y = parseInt(m.group(2));
            int c = m.group(3).charAt(0);
            long count = m.group(4).chars().filter(i -> i == c).count();
            return x <= count && count <= y;
        }).count();
    }

    public long a2() {
        return input.stream().map(p::matcher).filter(m -> {
            if (!m.find()) throw new IllegalArgumentException();
            int x = parseInt(m.group(1));
            int y = parseInt(m.group(2));
            int c = m.group(3).charAt(0);
            String pw = m.group(4);
            return (pw.charAt(x - 1) == c) != (pw.charAt(y - 1) == c);
        }).count();

    }

}
