package de.madjosz.adventofcode.y2015;

import static java.lang.Math.min;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;


public class Day02 {

    private final List<int[]> presents;

    public Day02(List<String> input) {
        this.presents = input.stream()
                .map(s -> s.split("x"))
                .map(d -> Arrays.stream(d).mapToInt(Integer::parseInt).toArray())
                .collect(toList());
    }

    public int a1() {
        return presents.stream().mapToInt(d -> {
            int a1 = d[0] * d[1], a2 = d[1] * d[2], a3 = d[2] * d[0];
            return 2 * (a1 + a2 + a3) + min(a1, min(a2, a3));
        }).sum();
    }

    public int a2() {
        return presents.stream()
                .mapToInt(d -> 2 * min(d[0] + d[1], min(d[1] + d[2], d[2] + d[0])) + d[0] * d[1] * d[2])
                .sum();
    }
}
