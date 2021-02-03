package de.madjosz.adventofcode.y2016;

import static java.util.stream.Collectors.toList;

import de.madjosz.adventofcode.data.Coord;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Day01 {

    private final List<String> input;

    public Day01(String input) {
        this.input = Arrays.stream(input.split(", ")).collect(toList());
    }

    public int a1() {
        int[] pos = new int[2];
        int dir = 1;
        for (int i = 0; i < input.size(); ++i) {
            String line = input.get(i);
            dir *= (line.charAt(0) == 'R' ? 1 : -1) * (i % 2 == 0 ? 1 : -1);
            pos[i % 2] += dir * Integer.parseInt(line.substring(1));
        }
        return Math.abs(pos[0]) + Math.abs(pos[1]);
    }

    public int a2() {
        Set<Coord> visited = new HashSet<>();
        visited.add(new Coord(0, 0));
        int[] pos = new int[2];
        int dir = 1;
        for (int i = 0; i < input.size(); ++i) {
            String line = input.get(i);
            dir *= (line.charAt(0) == 'R' ? 1 : -1) * (i % 2 == 0 ? 1 : -1);
            int steps = Integer.parseInt(line.substring(1));
            for (int j = 0; j < steps; ++j) {
                pos[i % 2] += dir;
                if (!visited.add(new Coord(pos))) return Math.abs(pos[0]) + Math.abs(pos[1]);
            }
        }
        throw new IllegalArgumentException();
    }
}
