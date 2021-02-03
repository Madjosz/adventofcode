package de.madjosz.adventofcode.y2016;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


public class Day03 {

    private final List<int[]> sides;

    public Day03(List<String> input) {
        this.sides = input.stream()
                .map(s -> s.split("\s+"))
                .map(p -> IntStream.rangeClosed(1, 3).map(i -> Integer.parseInt(p[i])).toArray())
                .collect(toList());
    }

    public int a1() {
        return (int) sides.stream().map(int[]::clone).filter(a -> {
            Arrays.sort(a);
            return a[0] + a[1] > a[2];
        }).count();
    }

    public int a2() {
        int count = 0;
        for (int i = 0; i < sides.size(); i += 3)
            for (int j = 0; j < 3; ++j) {
                int[] a = new int[] { sides.get(i)[j], sides.get(i + 1)[j], sides.get(i + 2)[j] };
                Arrays.sort(a);
                if (a[0] + a[1] > a[2]) ++count;
            }
        return count;
    }
}
