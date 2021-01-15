package de.madjosz.adventofcode.y2015;

import java.util.regex.Pattern;
import java.util.stream.Stream;


public class Day10 {

    private final String input;

    public Day10(String input) {
        this.input = input;
    }

    public int a1() {
        return lookAndSay(40).length();
    }

    String lookAndSay(int applications) {
        return Stream.iterate(input, Day10::lookAndSay).limit(applications + 1L).reduce((a, b) -> b).orElse(input);
    }

    private static String lookAndSay(String value) {
        return P.matcher(value).replaceAll(m -> m.group().length() + m.group(1));
    }

    private static final Pattern P = Pattern.compile("(\\d)\\1*");

    public int a2() {
        return lookAndSay(50).length();
    }
}
