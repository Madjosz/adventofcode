package de.madjosz.adventofcode.y2020;

import java.util.List;


public class Day01 {

    private final int[] list;

    public Day01(List<String> input) {
        this.list = input.stream().mapToInt(Integer::parseInt).toArray();
    }

    public int a1() {
        for (int j = 0; j < list.length; ++j)
            for (int k = j + 1; k < list.length; ++k)
                if (list[j] + list[k] == 2020) return list[j] * list[k];
        throw new IllegalArgumentException();
    }

    public int a2() {
        for (int j = 0; j < list.length; ++j)
            for (int k = j + 1; k < list.length; ++k)
                for (int l = k + 1; l < list.length; ++l)
                    if (list[j] + list[k] + list[l] == 2020) return list[j] * list[k] * list[l];
        throw new IllegalArgumentException();
    }
}
