package de.madjosz.adventofcode.y2020;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Day06 {

    private final List<String> answers;

    public Day06(List<String> input) {
        this.answers = input;
    }

    public int a1() {
        Set<Integer> currentAnswers = new HashSet<>();
        int counter = 0;
        for (String line : answers) {
            if (line.isBlank()) {
                counter += currentAnswers.size();
                currentAnswers.clear();
            } else line.chars().forEach(currentAnswers::add);
        }
        counter += currentAnswers.size();
        return counter;
    }

    public int a2() {
        Map<Integer, Integer> currentAnswers = new LinkedHashMap<>();
        int counter = 0;
        int people = 0;
        for (String line : answers) {
            if (line.isBlank()) {
                counter += count(currentAnswers, people);
                currentAnswers.clear();
                people = 0;
            } else {
                line.chars().forEach(c -> currentAnswers.merge(c, 1, (a, b) -> a + b));
                ++people;
            }
        }
        counter += count(currentAnswers, people);
        return counter;
    }

    private static long count(Map<Integer, Integer> answers, int people) {
        return answers.values().stream().filter(c -> c == people).count();
    }
}