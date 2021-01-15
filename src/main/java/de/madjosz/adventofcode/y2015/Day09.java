package de.madjosz.adventofcode.y2015;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day09 {

    private final Map<String, Map<String, Integer>> distances;
    private static final Pattern PARSER = Pattern.compile("(\\w+) to (\\w+) = (\\d+)");

    public Day09(List<String> input) {
        this.distances = new HashMap<>();
        input.stream().map(PARSER::matcher).filter(Matcher::find).forEach(m -> {
            Integer distance = Integer.valueOf(m.group(3));
            String city1 = m.group(1);
            String city2 = m.group(2);
            distances.computeIfAbsent(city1, k -> new LinkedHashMap<>()).put(city2, distance);
            distances.computeIfAbsent(city2, k -> new HashMap<>()).put(city1, distance);
        });
    }

    public int a1() {
        return findExtremeRoute(distances.keySet())[0];
    }

    private int[] findExtremeRoute(Collection<String> cities) {
        int[] extreme = new int[] { Integer.MAX_VALUE, 0 };
        for (int i = 0; i < cities.size(); ++i) {
            List<String> otherCities = new ArrayList<>(cities);
            String city = otherCities.remove(i);
            int[] distance = findExtremeRoute(city, otherCities);
            extreme[0] = Math.min(extreme[0], distance[0]);
            extreme[1] = Math.max(extreme[1], distance[1]);
        }
        return extreme;
    }

    private int[] findExtremeRoute(String start, List<String> otherCities) {
        if (otherCities.size() == 1) {
            Integer distance = distances.get(start).get(otherCities.get(0));
            return new int[] { distance, distance };
        }
        int[] extreme = new int[] { Integer.MAX_VALUE, 0 };
        for (int i = 0; i < otherCities.size(); ++i) {
            List<String> cities = new ArrayList<>(otherCities);
            String city = cities.remove(i);
            int distance = distances.get(start).get(city);
            int[] route = findExtremeRoute(city, cities);
            extreme[0] = Math.min(extreme[0], distance + route[0]);
            extreme[1] = Math.max(extreme[1], distance + route[1]);
        }
        return extreme;
    }

    public int a2() {
        return findExtremeRoute(distances.keySet())[1];
    }
}
