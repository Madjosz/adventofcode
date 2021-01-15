package de.madjosz.adventofcode.y2015;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day08 {

    private final List<String> input;

    public Day08(List<String> input) {
        this.input = input;
    }

    public int a1() {
        int sum = 2 * input.size();
        for (String line : input) {
            for (int i = 0; i < line.length(); ++i)
                if (line.charAt(i) == '\\') {
                    ++sum;
                    if (line.charAt(++i) == 'x') {
                        sum += 2;
                        i += 2;
                    }
                }
        }
        return sum;
    }

    private static final Pattern ESCAPE = Pattern.compile("[\\\\\"]");

    public int a2() {
        return (int) input.stream().map(ESCAPE::matcher).flatMap(Matcher::results).count() + 2 * input.size();
    }
}
