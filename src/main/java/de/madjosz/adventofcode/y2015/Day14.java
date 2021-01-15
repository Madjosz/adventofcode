package de.madjosz.adventofcode.y2015;

import static java.lang.Integer.parseInt;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day14 {

    private final int[][] reindeers;

    private static final Pattern REINDEER = Pattern
            .compile("(\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+)");

    public Day14(List<String> input) {
        this.reindeers = input.stream()
                .map(REINDEER::matcher)
                .filter(Matcher::find)
                .map(m -> new int[] { parseInt(m.group(1)), parseInt(m.group(2)), parseInt(m.group(3)) })
                .toArray(int[][]::new);
    }

    public int a1() {
        return a1(2503);
    }

    int a1(int stop) {
        int max = 0;
        for (int[] movement : reindeers) {
            int speed = movement[0];
            int runtime = movement[1];
            int cycle = movement[1] + movement[2];
            int distance = ((stop / cycle) * runtime + Math.min(stop % cycle, runtime)) * speed;
            if (distance > max) max = distance;
        }
        return max;
    }

    public int a2() {
        return a2(2503);
    }

    int a2(int stop) {
        int[] positions = new int[reindeers.length];
        int[] scores = new int[reindeers.length];
        int[] cycles = Arrays.stream(reindeers).mapToInt(r -> r[1] + r[2]).toArray();
        for (int t = 0; t < stop; ++t) {
            for (int i = 0; i < positions.length; ++i)
                if (t % cycles[i] < reindeers[i][1]) positions[i] += reindeers[i][0];
            int max = Arrays.stream(positions).max().getAsInt();
            for (int i = 0; i < positions.length; ++i)
                if (positions[i] == max) ++scores[i];
        }
        return Arrays.stream(scores).max().getAsInt();
    }
}
