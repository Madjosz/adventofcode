package de.madjosz.adventofcode.y2020;

import java.util.List;

public class Day10 {

    private final int[] adapters;

    public Day10(List<String> input) {
        this.adapters = input.stream().mapToInt(Integer::parseInt).sorted().toArray();
    }

    public int a1() {
        int[] counter = new int[] { 0, 0, 1 };
        ++counter[adapters[0] - 1];
        for (int i = 0; i < adapters.length - 1; ++i)
            ++counter[adapters[i + 1] - adapters[i] - 1];
        return counter[0] * counter[2];
    }

    public long a2() {
        long[][] m = adjacencyMatrix();

        long sum = m[0][adapters.length + 1];
        long[][] m2 = m;
        for (int i = 0; i < adapters.length; ++i) {
            m2 = matmul(m2, m);
            sum += m2[0][adapters.length + 1];
        }
        return sum;
    }

    private long[][] adjacencyMatrix() {
        long[][] m = new long[adapters.length + 2][adapters.length + 2];
        for (int i = 0; i < 3; ++i)
            if (adapters[i] < 4) m[0][i + 1] = 1L;
        for (int i = 0; i < adapters.length; ++i)
            for (int j = i + 1; j < i + 4 && j < adapters.length; ++j)
                if (adapters[j] - adapters[i] < 4) m[i + 1][j + 1] = 1L;
        m[adapters.length][adapters.length + 1] = 1L;
        return m;
    }

    static long[][] matmul(long[][] m1, long[][] m2) {
        int n = m1.length;
        long[][] m3 = new long[n][n];
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = i + 1; k < j; ++k)
                    m3[i][j] += m1[i][k] * m2[k][j];
        return m3;
    }

}
