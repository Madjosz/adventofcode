package de.madjosz.adventofcode.y2020;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toList;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Day07 {

    private final List<String> rules;

    public Day07(List<String> input) {
        this.rules = input;
    }

    private static final Pattern bagContent = Pattern.compile("(\\d+) ([a-z ]+) bags?");
    private static final String COLOR = "shiny gold";

    public int a1() {
        Map<String, Set<String>> bags = parseInverseRules();
        Queue<String> todo = new ArrayDeque<>();
        todo.add(COLOR);
        Set<String> colors = new HashSet<>();
        while (!todo.isEmpty()) {
            String color = todo.poll();
            if (colors.add(color)) todo.addAll(bags.getOrDefault(color, emptySet()));
        }
        return colors.size() - 1;
    }

    private Map<String, Set<String>> parseInverseRules() {
        Map<String, Set<String>> bags = new HashMap<>();
        rules.stream()
                .map(s -> s.split(" bags contain "))
                .forEach(s -> bagContent.matcher(s[1])
                        .results()
                        .forEach(r -> bags.merge(r.group(2), new HashSet<>(asList(s[0])), (l1, l2) -> {
                            l1.addAll(l2);
                            return l1;
                        })));
        return bags;
    }

    public int a2() {
        Map<String, List<Bags>> bags = parseRules();
        return getChildBags(bags, COLOR) - 1;
    }

    private Map<String, List<Bags>> parseRules() {
        return rules.stream()
                .map(s -> s.split(" bags contain "))
                .collect(Collectors.toMap(s -> s[0],
                        s -> bagContent.matcher(s[1]).results().map(Bags::new).collect(toList())));
    }

    private static int getChildBags(Map<String, List<Bags>> bags, String color) {
        return bags.getOrDefault(color, emptyList())
                .stream()
                .mapToInt(b -> b.count() * getChildBags(bags, b.color()))
                .sum() + 1;
    }

    private record Bags(int count, String color) {

        private Bags(MatchResult r) {
            this(Integer.parseInt(r.group(1)), r.group(2));
        }
    }

}
