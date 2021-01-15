package de.madjosz.adventofcode.y2015;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


public class Day17 {

    private final int[] containers;

    public Day17(List<String> input) {
        this.containers = input.stream().mapToInt(Integer::parseInt).sorted().toArray();
    }

    public int a1() {
        return a1(150);
    }

    int a1(int liters) {
        return fillRemaining(liters, 0);
    }

    private int fillRemaining(int toFill, int start) {
        int sum = 0;
        for (int i = start; i < containers.length; ++i)
            sum += fillRemainingWith(i, toFill);
        return sum;
    }

    private int fillRemainingWith(int i, int toFill) {
        int remaining = toFill - containers[i];
        if (remaining < 0) return 0;
        if (remaining == 0) return 1;
        return fillRemaining(remaining, i + 1);
    }

    public int a2() {
        return a2(150);
    }

    int a2(int liters) {
        return new ContainerCounter(liters).combos.values().iterator().next();
    }

    private class ContainerCounter {

        private final SortedMap<Integer, Integer> combos = new TreeMap<>();

        public ContainerCounter(int liters) {
            countContainers(liters, 0, 0);
        }

        private void countContainers(int toFill, int start, int usedContainers) {
            for (int i = start; i < containers.length; ++i)
                countContainersWith(i, toFill, usedContainers);
        }

        private void countContainersWith(int i, int toFill, int used) {
            int remaining = toFill - containers[i];
            if (remaining == 0) combos.merge(used + 1, 1, Integer::sum);
            else if (remaining > 0) countContainers(remaining, i + 1, used + 1);
        }
    }
}
