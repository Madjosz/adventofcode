package de.madjosz.adventofcode.y2020;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Predicate;


public class Day21 {

    private final List<String> ingredients;

    public Day21(List<String> input) {
        this.ingredients = input;
    }

    public int a1() {
        Set<String> withAllergenes = parseAllergenes().keySet();
        return (int) ingredients.stream()
                .map(s -> s.substring(0, s.indexOf('(') - 1))
                .map(s -> s.split(" "))
                .flatMap(Arrays::stream)
                .filter(Predicate.not(withAllergenes::contains))
                .count();
    }

    private Map<String, String> parseAllergenes() {
        Map<String, Set<String>> possibles = new LinkedHashMap<>();
        for (String line : ingredients) {
            int brace = line.indexOf('(');
            List<String> z = asList(line.substring(0, brace - 1).split(" "));
            for (String allergene : line.substring(brace + 10, line.length() - 1).split(", ")) {
                possibles.merge(allergene, new HashSet<>(z), (s1, s2) -> {
                    s1.retainAll(s2);
                    return s1;
                });
            }
        }
        Map<String, String> withAllergenes = new LinkedHashMap<>();
        while (!possibles.isEmpty()) {
            Entry<String, Set<String>> exact = possibles.entrySet()
                    .stream()
                    .filter(e -> e.getValue().size() == 1)
                    .findAny()
                    .orElseThrow();
            String allergene = exact.getValue().iterator().next();
            withAllergenes.put(allergene, exact.getKey());
            possibles.remove(exact.getKey());
            possibles.values().stream().forEach(s -> s.remove(allergene));
        }
        return withAllergenes;
    }

    public String a2() {
        return parseAllergenes().entrySet()
                .stream()
                .collect(toMap(Entry::getValue, Entry::getKey, (a, b) -> a, TreeMap::new))
                .values()
                .stream()
                .collect(joining(","));
    }

}
