package de.madjosz.adventofcode.y2015;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;


public class Day05 {

    private final List<String> input;

    public Day05(List<String> input) {
        this.input = input;
    }

    private static Predicate<String> threeVowels = Pattern.compile("[aeiou].*[aeiou].*[aeiou]").asPredicate();
    private static Predicate<String> doubleLetter = Pattern.compile("(.)\\1").asPredicate();
    private static Predicate<String> forbidden = Predicate.not(Pattern.compile("ab|cd|pq|xy").asPredicate());

    public int a1() {
        return (int) input.stream().filter(threeVowels).filter(doubleLetter).filter(forbidden).count();
    }

    private static Predicate<String> doublePair = Pattern.compile("(..).*\\1").asPredicate();
    private static Predicate<String> letterInBetween = Pattern.compile("(.).\\1").asPredicate();

    public int a2() {
        return (int) input.stream().filter(doublePair).filter(letterInBetween).count();
    }
}
