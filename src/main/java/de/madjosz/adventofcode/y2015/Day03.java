package de.madjosz.adventofcode.y2015;

import de.madjosz.adventofcode.data.Coord;
import java.util.HashSet;
import java.util.Set;


public class Day03 {

    private final String input;

    public Day03(String input) {
        this.input = input;
    }

    public int a1() {
        Set<Coord> visited = new HashSet<>();
        int[] current = new int[2];
        visited.add(new Coord(current));
        for (char d : input.toCharArray()) {
            switch (d) {
            case '^':
                ++current[0];
                break;
            case '>':
                ++current[1];
                break;
            case 'v':
                --current[0];
                break;
            case '<':
                --current[1];
                break;
            default:
                throw new IllegalArgumentException();
            }
            visited.add(new Coord(current));
        }
        return visited.size();
    }

    public int a2() {
        Set<Coord> visited = new HashSet<>();
        int[][] current = new int[2][2];
        int index = 0;
        visited.add(new Coord(current[0]));
        for (char d : input.toCharArray()) {
            switch (d) {
            case '^':
                ++current[index % 2][0];
                break;
            case '>':
                ++current[index % 2][1];
                break;
            case 'v':
                --current[index % 2][0];
                break;
            case '<':
                --current[index % 2][1];
                break;
            default:
                throw new IllegalArgumentException();
            }
            visited.add(new Coord(current[index++ % 2]));
        }
        return visited.size();
    }
}
