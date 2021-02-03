package de.madjosz.adventofcode.data;

import java.util.List;

public record Coord(int x, int y) {

    public static List<int[]> DIRECTIONS = List.of(new int[] { -1, 0 }, new int[] { 1, 0 }, new int[] { 0, -1 },
            new int[] { 0, 1 });

    public Coord(int[] c) {
        this(c[0], c[1]);
    }

    public Coord add(int[] dir) {
        return new Coord(x + dir[0], y + dir[1]);
    }
}
