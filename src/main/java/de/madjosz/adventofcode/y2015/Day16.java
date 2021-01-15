package de.madjosz.adventofcode.y2015;

import static java.util.function.Predicate.isEqual;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Predicate;


public class Day16 {

    private final List<String[]> sues;

    public Day16(List<String> input) {
        this.sues = input.stream().map(s -> s.substring(s.indexOf(':') + 2).split(", ")).collect(toList());
    }

    private static final Map<String, String> TICKERTAPE = Map.of( //
            "children", "3", //
            "cats", "7", //
            "samoyeds", "2", //
            "pomeranians", "3", //
            "akitas", "0", //
            "vizslas", "0", //
            "goldfish", "5", //
            "trees", "3", //
            "cars", "2", //
            "perfumes", "1"//
    );

    public int a1() {
        for (int i = 0; i < sues.size(); ++i) {
            if (checkSue(sues.get(i))) return i + 1;
        }
        throw new NoSuchElementException();
    }

    private boolean checkSue(String[] sue) {
        for (String compound : sue) {
            String[] parts = compound.split(": ");
            String expected = TICKERTAPE.get(parts[0]);
            if (!expected.equals(parts[1])) return false;
        }
        return true;
    }

    private static Predicate<String> gt(int x) {
        return s -> x < Integer.parseInt(s);
    }

    private static Predicate<String> lt(int x) {
        return s -> x > Integer.parseInt(s);
    }

    private static final Map<String, Predicate<String>> TICKERTAPE2 = Map.of( //
            "children", isEqual("3"), //
            "cats", gt(7), //
            "samoyeds", isEqual("2"), //
            "pomeranians", lt(3), //
            "akitas", isEqual("0"), //
            "vizslas", isEqual("0"), //
            "goldfish", lt(5), //
            "trees", gt(3), //
            "cars", isEqual("2"), //
            "perfumes", isEqual("1")//
    );

    public int a2() {
        for (int i = 0; i < sues.size(); ++i) {
            if (checkSue2(sues.get(i))) return i + 1;
        }
        throw new NoSuchElementException();
    }

    private boolean checkSue2(String[] sue) {
        for (String compound : sue) {
            String[] parts = compound.split(": ");
            Predicate<String> expected = TICKERTAPE2.get(parts[0]);
            if (!expected.test(parts[1])) return false;
        }
        return true;
    }
}
