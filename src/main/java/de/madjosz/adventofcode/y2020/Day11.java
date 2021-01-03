package de.madjosz.adventofcode.y2020;

import java.util.Arrays;
import java.util.List;


public class Day11 {

    private final boolean[][] seats;
    private final int rows;
    private final int cols;

    public Day11(List<String> input) {
        this.seats = input.stream().map(l -> {
            boolean[] b = new boolean[l.length()];
            for (int i = 0; i < l.length(); ++i)
                b[i] = l.charAt(i) == 'L';
            return b;
        }).toArray(boolean[][]::new);
        this.rows = seats.length;
        this.cols = seats[0].length;
    }

    public int a1() {
        return iterate(this::rule1);
    }

    private int iterate(Rule rule) {
        boolean[][] alive = new boolean[rows][cols];
        boolean hasChanged;
        do {
            hasChanged = false;
            boolean[][] newGen = new boolean[rows][cols];
            for (int row = 0; row < rows; ++row)
                for (int col = 0; col < cols; ++col)
                    if (seats[row][col]) hasChanged |= rule.apply(alive, newGen, row, col);
            alive = newGen;
        } while (hasChanged);
        return Arrays.stream(alive).mapToInt(a -> {
            int c = 0;
            for (boolean b : a)
                if (b) ++c;
            return c;
        }).sum();
    }

    private boolean rule1(boolean[][] alive, boolean[][] newGen, int row, int col) {
        int n = aliveNeighbors1(alive, row, col);
        if (!alive[row][col] && n == 0) {
            newGen[row][col] = true;
            return true;
        } else if (alive[row][col] && n > 3) {
            return true;
        } else newGen[row][col] = alive[row][col];
        return false;
    }

    private int aliveNeighbors1(boolean[][] alive, int row, int col) {
        return (int) Arrays.stream(
                new int[][] { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } })
                .filter(d -> aliveNeighbor1(alive, row, col, d))
                .count();
    }

    private boolean aliveNeighbor1(boolean[][] alive, int row, int col, int[] dir) {
        int r = row + dir[0];
        int c = col + dir[1];
        return checkRow(r) && checkCol(c) && alive[r][c];
    }

    private boolean checkRow(int r) {
        return 0 <= r && r < rows;
    }

    private boolean checkCol(int c) {
        return 0 <= c && c < cols;
    }

    public int a2() {
        return iterate(this::rule2);
    }

    private boolean rule2(boolean[][] alive, boolean[][] newGen, int row, int col) {
        int n = aliveNeighbors2(alive, row, col);
        if (!alive[row][col] && n == 0) {
            newGen[row][col] = true;
            return true;
        } else if (alive[row][col] && n > 4) {
            return true;
        } else newGen[row][col] = alive[row][col];
        return false;
    }

    private int aliveNeighbors2(boolean[][] alive, int row, int col) {
        return (int) Arrays.stream(
                new int[][] { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } })
                .filter(d -> aliveNeighbor2(alive, row, col, d))
                .count();
    }

    private boolean aliveNeighbor2(boolean[][] alive, int row, int col, int[] dir) {
        int r = row;
        int c = col;
        do {
            r += dir[0];
            c += dir[1];
        } while (checkRow(r) && checkCol(c) && !seats[r][c]);
        return checkRow(r) && checkCol(c) && alive[r][c];
    }

    private interface Rule {

        boolean apply(boolean[][] alive, boolean[][] newGen, int row, int col);
    }

}
