package de.madjosz.adventofcode.y2015;

import static java.lang.Integer.parseInt;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;


public class Day06 {

    private final List<String> input;

    public Day06(List<String> input) {
        this.input = input;
    }

    private static final Pattern command = Pattern
            .compile("(?:turn )?(on|off|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)");

    public int a1() {
        boolean[][] lamps = new boolean[1000][1000];
        for (String line : input) {
            Matcher m = command.matcher(line);
            if (!m.find()) throw new IllegalArgumentException(line);
            int startX = parseInt(m.group(2));
            int startY = parseInt(m.group(3));
            int endX = parseInt(m.group(4));
            int endY = parseInt(m.group(5));
            switch (m.group(1)) {
            case "on":
                for (int x = startX; x <= endX; ++x)
                    for (int y = startY; y <= endY; ++y)
                        lamps[x][y] = true;
                break;
            case "off":
                for (int x = startX; x <= endX; ++x)
                    for (int y = startY; y <= endY; ++y)
                        lamps[x][y] = false;
                break;
            case "toggle":
                for (int x = startX; x <= endX; ++x)
                    for (int y = startY; y <= endY; ++y)
                        lamps[x][y] = !lamps[x][y];
                break;
            }
        }
        return (int) Arrays.stream(lamps).mapToLong(b -> IntStream.range(0, b.length).filter(i -> b[i]).count()).sum();
    }

    public int a2() {
        int[][] lamps = new int[1000][1000];
        for (String line : input) {
            Matcher m = command.matcher(line);
            if (!m.find()) throw new IllegalArgumentException(line);
            int startX = parseInt(m.group(2));
            int startY = parseInt(m.group(3));
            int endX = parseInt(m.group(4));
            int endY = parseInt(m.group(5));
            switch (m.group(1)) {
            case "on":
                for (int x = startX; x <= endX; ++x)
                    for (int y = startY; y <= endY; ++y)
                        ++lamps[x][y];
                break;
            case "off":
                for (int x = startX; x <= endX; ++x)
                    for (int y = startY; y <= endY; ++y)
                        if (lamps[x][y] > 0) --lamps[x][y];
                break;
            case "toggle":
                for (int x = startX; x <= endX; ++x)
                    for (int y = startY; y <= endY; ++y)
                        lamps[x][y] += 2;
                break;
            }
        }
        return Arrays.stream(lamps).flatMapToInt(Arrays::stream).sum();
    }
}
