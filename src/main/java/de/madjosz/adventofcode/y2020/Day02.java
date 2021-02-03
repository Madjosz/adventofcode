package de.madjosz.adventofcode.y2020;

import static java.lang.Integer.parseInt;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day02 {

    private final List<String> input;

    public Day02(List<String> input) {
        this.input = input;
    }

    private static final Pattern p = Pattern.compile("^(\\d+)-(\\d+) (\\w): (\\w+)$");

    private record Policy(int a, int b, char c, String pw) {

        Policy(Matcher m) {
            this(parseInt(m.group(1)), parseInt(m.group(2)), m.group(3).charAt(0), m.group(4));
        }
    }

    public long a1() {
        return input.stream().map(p::matcher).filter(Matcher::find).map(Policy::new).filter(p -> {
            long count = p.pw().chars().filter(i -> i == p.c()).count();
            return p.a() <= count && count <= p.b();
        }).count();
    }

    public long a2() {
        return input.stream()
                .map(p::matcher)
                .filter(Matcher::find)
                .map(Policy::new)
                .filter(p -> (p.pw().charAt(p.a() - 1) == p.c()) != (p.pw().charAt(p.b() - 1) == p.c()))
                .count();

    }

}
