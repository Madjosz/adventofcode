package de.madjosz.adventofcode.y2016;

import static java.util.function.Predicate.not;

import java.util.List;
import java.util.regex.Pattern;


public class Day07 {

    private final List<String> input;

    public Day07(List<String> input) {
        this.input = input;
    }

    private static final Pattern ABBA = Pattern.compile("(.)(?!\\1)(.)\\2\\1");
    private static final Pattern HYPERNET = Pattern.compile("\\[\\w*" + ABBA.pattern() + "\\w*\\]");

    public int a1() {
        return (int) input.stream().filter(ABBA.asPredicate()).filter(not(HYPERNET.asPredicate())).count();
    }

    private static final Pattern ABA = Pattern.compile(
            "(?:(.)(?!\\1)(.)\\1(?:\\w*\\[\\w+\\])*\\w*\\[\\w*\\2\\1\\2)|(?:(.)(?!\\3)(.)\\3)\\w*\\](?:\\w*\\[\\w+\\])*\\w*\\4\\3\\4");

    public int a2() {
        return (int) input.stream().filter(ABA.asPredicate()).count();
    }
}
