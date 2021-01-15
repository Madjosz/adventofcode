package de.madjosz.adventofcode.y2015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Day24 {

    private final int[] packages;

    public Day24(List<String> input) {
        this.packages = input.stream().mapToInt(Integer::parseInt).toArray();
    }

    public long a1() {
        return splitThePackages(3);
    }

    private int getPackagesPerGroup(int groups) {
        int total = Arrays.stream(packages).sum();
        if (total % groups != 0) throw new IllegalArgumentException();
        return total / groups;
    }

    private long splitThePackages(int groups) {
        int perGroup = getPackagesPerGroup(groups);
        for (int i = 1; i < packages.length / 3; ++i) {
            long quantum = Long.MAX_VALUE;
            for (int[] combi : combinations(packages.length, i)) {
                long result = checkCombination(packages, combi, perGroup, groups - 1);
                if (result < quantum) quantum = result;
            }
            if (quantum != Long.MAX_VALUE) return quantum;
        }
        throw new IllegalArgumentException();
    }

    private static List<int[]> combinations(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        int[] combination = new int[r];
        for (int i = 0; i < r; i++)
            combination[i] = i;

        while (combination[r - 1] < n) {
            combinations.add(combination.clone());
            int t = r - 1;
            while (t != 0 && combination[t] == n - r + t)
                t--;
            combination[t]++;
            for (int i = t + 1; i < r; i++)
                combination[i] = combination[i - 1] + 1;
        }
        return combinations;
    }

    private static long checkCombination(int[] packs, int[] indices, int perGroup, int groupsToGo) {
        if (weight(indices, packs) == perGroup) {
            int[] rest = remove(packs, indices);
            for (int i = 1; i < rest.length / 2; ++i)
                for (int[] combi : combinations(rest.length, i))
                    if (weight(combi, rest) == perGroup && (groupsToGo == 2
                            || checkCombination(rest, combi, perGroup, groupsToGo - 1) != Long.MAX_VALUE))
                        return quantum(packs, indices);
        }
        return Long.MAX_VALUE;
    }

    private static int[] remove(int[] packs, int[] indices) {
        int[] rest = new int[packs.length - indices.length];
        for (int i = 0, k = 0; i < packs.length; ++i) {
            if (Arrays.binarySearch(indices, i) >= 0) rest[k++] = packs[i];
        }
        return rest;
    }

    private static int weight(int[] indices, int[] packs) {
        return Arrays.stream(indices).map(i -> packs[i]).sum();
    }

    private static long quantum(int[] packs, int[] indices) {
        return Arrays.stream(indices).map(i -> packs[i]).mapToLong(i -> i).reduce((a, b) -> a * b).getAsLong();
    }

    public long a2() {
        return splitThePackages(4);
    }
}
