package de.madjosz.adventofcode.y2020;

import java.util.Arrays;
import java.util.List;
import java.util.function.ToLongFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day18 {

    private final List<String> homework;

    public Day18(List<String> input) {
        this.homework = input;
    }

    private static final Pattern parens = Pattern.compile("\\(([\\d *+]+)\\)");

    public long a1() {
        return homework.stream().mapToLong(h -> parse(h, Day18::calc)).sum();
    }

    private static long calc(String term) {
        String[] parts = term.split(" ");
        long result = Long.parseLong(parts[0]);
        for (int i = 1; i < parts.length; i += 2) {
            long number = Long.parseLong(parts[i + 1]);
            switch (parts[i]) {
            case "+":
                result += number;
                break;
            case "*":
                result *= number;
                break;
            default:
                throw new IllegalArgumentException(parts[i]);
            }
        }
        return result;
    }

    public long a2() {
        return homework.stream().mapToLong(h -> parse(h, Day18::calc2)).sum();
    }

    private static long parse(String line, ToLongFunction<String> calc) {
        Matcher m = parens.matcher(line);
        while (m.find()) {
            line = m.replaceAll(r -> Long.toString(calc.applyAsLong(r.group(1))));
            m = parens.matcher(line);
        }
        return calc.applyAsLong(line);
    }

    private static long calc2(String term) {
        return Arrays.stream(term.split(" \\* "))
                .mapToLong(t -> Arrays.stream(t.split(" \\+ ")).mapToLong(Long::parseLong).sum())
                .reduce(1L, (a, b) -> a * b);
    }
}
