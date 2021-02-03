package de.madjosz.adventofcode.y2016;

import de.madjosz.adventofcode.data.Coord;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.ToIntFunction;


public class Day24 {

    private final List<String> input;

    public Day24(List<String> input) {
        this.input = input;
    }

    public int a1() {
        Map<Integer, Coord> numbers = findNumbers();
        var distances = calculateDistances(numbers);
        return findShortestRoute(distances.keySet(), i -> getPathLength(i, distances));
    }

    private record Path(Coord coord, int step) {}

    private Map<Integer, Coord> findNumbers() {
        Map<Integer, Coord> coords = new HashMap<>();
        for (int y = 0; y < input.size(); ++y) {
            String line = input.get(y);
            for (int x = 0; x < line.length(); ++x)
                if (isNumber(line, x)) coords.put(line.charAt(x) - '0', new Coord(x, y));
        }
        return coords;
    }

    private boolean isNumber(String line, int x) {
        return Character.isDigit(line.charAt(x));
    }

    private Map<Integer, Map<Integer, Integer>> calculateDistances(Map<Integer, Coord> numbers) {
        Map<Integer, Map<Integer, Integer>> distances = new HashMap<>();

        for (var start : numbers.entrySet()) {
            HashMap<Integer, Integer> dist = new HashMap<>();
            Set<Coord> seen = new HashSet<>();
            Queue<Path> open = new ArrayDeque<>();
            seen.add(start.getValue());
            open.offer(new Path(start.getValue(), 0));
            while (!open.isEmpty() && dist.size() != numbers.size()) {
                Path current = open.poll();
                Coord pos = current.coord();
                String line = input.get(pos.y());
                if (isNumber(line, pos.x())) dist.put(line.charAt(pos.x()) - '0', current.step);
                for (int[] dir : Coord.DIRECTIONS) {
                    Coord nextPos = pos.add(dir);
                    if (seen.add(nextPos) && !isWall(nextPos)) open.offer(new Path(nextPos, current.step() + 1));
                }
            }
            distances.put(start.getKey(), dist);
        }
        return distances;
    }

    private boolean isWall(Coord c) {
        return input.get(c.y()).charAt(c.x()) == '#';
    }

    private int findShortestRoute(Collection<Integer> goals, ToIntFunction<int[]> getPathLength) {
        int[] order = goals.stream().mapToInt(i -> i).filter(i -> i != 0).sorted().toArray();
        return permutations(order).stream().mapToInt(getPathLength).min().orElseThrow();
    }

    private static List<int[]> permutations(int[] elements) {
        List<int[]> results = new ArrayList<>(factorial(elements.length));
        permutations(elements.length, elements, results);
        return results;
    }

    private static void permutations(int n, int[] elements, List<int[]> results) {
        if (n == 1) results.add(elements.clone());
        else {
            for (int i = 0; i < n - 1; i++) {
                permutations(n - 1, elements, results);
                if (n % 2 == 0) swap(elements, i, n - 1);
                else swap(elements, 0, n - 1);
            }
            permutations(n - 1, elements, results);
        }
    }

    private static int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

    private static void swap(int[] elements, int i, int j) {
        int tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;
    }

    private int getPathLength(int[] order, Map<Integer, Map<Integer, Integer>> distances) {
        int last = 0;
        int sum = 0;
        for (int next : order) {
            sum += distances.get(last).get(next);
            last = next;
        }
        return sum;
    }

    public int a2() {
        Map<Integer, Coord> numbers = findNumbers();
        var distances = calculateDistances(numbers);
        return findShortestRoute(distances.keySet(), i -> getCircleLength(i, distances));
    }

    private int getCircleLength(int[] order, Map<Integer, Map<Integer, Integer>> distances) {
        int last = 0;
        int sum = 0;
        for (int next : order) {
            sum += distances.get(last).get(next);
            last = next;
        }
        return sum + distances.get(last).get(0);
    }
}
