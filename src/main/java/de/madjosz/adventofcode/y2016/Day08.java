package de.madjosz.adventofcode.y2016;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


public class Day08 {

    private final List<String> input;

    public Day08(List<String> input) {
        this.input = input;
    }

    public int a1() {
        return a1(50, 6);
    }

    int a1(int width, int height) {
        boolean[][] screen = fillScreen(width, height);
        return (int) Arrays.stream(screen).mapToLong(b -> IntStream.range(0, b.length).filter(i -> b[i]).count()).sum();
    }

    private boolean[][] fillScreen(int width, int height) {
        boolean[][] screen = new boolean[height][width];
        for (String instruction : input) {
            if (instruction.startsWith("rect")) rect(instruction, screen);
            else rotate(instruction, screen);
        }
        return screen;
    }

    private void rect(String instruction, boolean[][] screen) {
        int[] dim = getParameters(instruction.substring(5), "x");
        for (int r = 0; r < dim[1]; ++r)
            for (int c = 0; c < dim[0]; ++c)
                screen[r][c] = true;
    }

    private void rotate(String instruction, boolean[][] screen) {
        if (instruction.contains("y=")) rotateRow(instruction, screen);
        else rotateCol(instruction, screen);
    }

    private void rotateRow(String instruction, boolean[][] screen) {
        int[] params = getParameters(instruction.substring(13), " by ");
        boolean[] row = screen[params[0]];
        boolean[] overflow = Arrays.copyOfRange(row, row.length - params[1], row.length);
        System.arraycopy(row, 0, row, params[1], row.length - params[1]);
        System.arraycopy(overflow, 0, row, 0, params[1]);
    }

    private void rotateCol(String instruction, boolean[][] screen) {
        int[] params = getParameters(instruction.substring(16), " by ");
        boolean[] overflow = new boolean[params[1]];
        for (int i = 0; i < params[1]; ++i)
            overflow[i] = screen[screen.length - params[1] + i][params[0]];
        for (int i = screen.length - 1; i >= params[1]; --i)
            screen[i][params[0]] = screen[i - params[1]][params[0]];
        for (int i = 0; i < params[1]; ++i)
            screen[i][params[0]] = overflow[i];
    }

    private static int[] getParameters(String instruction, String splitter) {
        return Arrays.stream(instruction.split(splitter)).mapToInt(Integer::parseInt).toArray();
    }

    public String a2() {
        return a2(50, 6);
    }

    String a2(int width, int height) {
        boolean[][] screen = fillScreen(width, height);
        return prettyPrint(screen);
    }

    private static String prettyPrint(boolean[][] screen) {
        return Arrays.stream(screen)
                .map(b -> IntStream.range(0, b.length)
                        .collect(StringBuilder::new, (s, i) -> s.append(b[i] ? '#' : ' '), StringBuilder::append)
                        .toString())
                .collect(joining("\n"));
    }
}
