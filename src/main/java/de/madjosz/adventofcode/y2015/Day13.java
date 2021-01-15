package de.madjosz.adventofcode.y2015;

import static java.util.Collections.emptyMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day13 {

    private final Map<String, Map<String, Integer>> happiness;

    private static final Pattern HAPPINESS_PATTERN = Pattern
            .compile("(\\w+) would (gain|lose) (\\d+) happiness units by sitting next to (\\w+)");

    public Day13(List<String> input) {
        Map<String, Map<String, Integer>> happiness = new LinkedHashMap<>();
        input.stream().map(HAPPINESS_PATTERN::matcher).filter(Matcher::find).forEach(m -> {
            int increase = Integer.parseInt(m.group(3));
            if ("lose".equals(m.group(2))) increase *= -1;
            happiness.computeIfAbsent(m.group(1), k -> new HashMap<>()).merge(m.group(4), increase, Integer::sum);
            happiness.computeIfAbsent(m.group(4), k -> new HashMap<>()).merge(m.group(1), increase, Integer::sum);
        });
        this.happiness = happiness;
    }

    public int a1() {
        List<String> people = new ArrayList<>(happiness.keySet());
        List<String> placedPeople = new ArrayList<>(8);
        placedPeople.add(people.get(0));
        while (placedPeople.size() != people.size())
            placedPeople.add(null);
        return getMaxHappiness(placedPeople, people.subList(1, people.size()));
    }

    private int getMaxHappiness(List<String> placedPeople, List<String> peopleToPlace) {
        if (peopleToPlace.isEmpty()) return getTotalHappiness(placedPeople);
        int max = 0;
        int idx = placedPeople.size() - peopleToPlace.size();
        for (int i = 0; i < peopleToPlace.size(); ++i) {
            List<String> remainingPeople = new ArrayList<>(peopleToPlace);
            placedPeople.set(idx, remainingPeople.remove(i));
            max = Math.max(max, getMaxHappiness(placedPeople, remainingPeople));
        }
        return max;
    }

    private int getTotalHappiness(List<String> people) {
        int sum = 0;
        for (int i = 0; i < people.size(); ++i)
            sum += happiness.getOrDefault(people.get(i), emptyMap())
                    .getOrDefault(people.get((i + 1) % people.size()), 0);
        return sum;
    }

    public int a2() {
        List<String> people = new ArrayList<>(happiness.keySet());
        List<String> placedPeople = new ArrayList<>(9);
        placedPeople.add("you");
        while (placedPeople.size() != people.size() + 1)
            placedPeople.add(null);
        return getMaxHappiness(placedPeople, people);
    }
}
