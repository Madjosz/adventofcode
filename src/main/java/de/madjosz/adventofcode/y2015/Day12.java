package de.madjosz.adventofcode.y2015;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day12 {

    private final String input;

    public Day12(String input) {
        this.input = input;
    }

    private static final Pattern NUMBERS = Pattern.compile("-?\\d+");

    public int a1() {
        return sumNumbers(input);
    }

    private static int sumNumbers(String str) {
        int sum = 0;
        Matcher m = NUMBERS.matcher(str);
        while (m.find())
            sum += Integer.parseInt(m.group());
        return sum;
    }

    private static final Pattern ARRAY = Pattern.compile("\\[[^{}\\[\\]]*\\]");
    private static final Pattern OBJECT = Pattern.compile("\\{[^{}\\[\\]]*\\}");

    public int a2() {
        String before;
        String after = input;
        do {
            before = after;
            after = ARRAY.matcher(after).replaceAll(m -> Integer.toString(sumNumbers(m.group())));
            after = OBJECT.matcher(after).replaceAll(m -> {
                if (m.group().contains("red")) return "0";
                return Integer.toString(sumNumbers(m.group()));
            });
        } while (!before.equals(after));

        return Integer.parseInt(after);
    }
}
