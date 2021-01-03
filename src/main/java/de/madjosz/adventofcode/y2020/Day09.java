package de.madjosz.adventofcode.y2020;

import java.util.Arrays;
import java.util.List;
import java.util.LongSummaryStatistics;


public class Day09 {

    private final long[] xmasData;
    private final int preamble;

    public Day09(List<String> input, int preamble) {
        this.xmasData = input.stream().mapToLong(Long::parseLong).toArray();
        this.preamble = preamble;
    }

    public long a1() {
        outer: for (int i = preamble; i < xmasData.length; ++i) {
            long current = xmasData[i];
            for (int j = i - preamble; j < i; ++j) {
                long diff = current - xmasData[j];
                for (int k = j + 1; k < i; ++k)
                    if (xmasData[k] == diff) continue outer;
            }
            return current;
        }
        return 0;
    }

    public long a2() {
        long invalid = a1();
        for (int i = xmasData.length - 2; i >= 0; --i) {
            long sum = xmasData[i] + xmasData[i + 1];
            int j = i;
            while (sum < invalid && j > 0)
                sum += xmasData[--j];
            if (sum == invalid) {
                LongSummaryStatistics summary = Arrays.stream(Arrays.copyOfRange(xmasData, j, i + 2))
                        .summaryStatistics();
                return summary.getMin() + summary.getMax();
            }
        }
        return 0;
    }

}
