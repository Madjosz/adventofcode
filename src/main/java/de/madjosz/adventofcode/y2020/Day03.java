package de.madjosz.adventofcode.y2020;

import java.util.List;


public class Day03 {

    private final List<String> trees;

    public Day03(List<String> input) {
        this.trees = input;
    }

    public int a1() {
        return countTrees(3, 1);
    }

    public long a2() {
        return 1L * countTrees(1, 1) * countTrees(3, 1) * countTrees(5, 1) * countTrees(7, 1) * countTrees(1, 2);
    }

    private int countTrees(int right, int down) {
        int pos = 0;
        int treeCounter = 0;
        for (int i = 0; i < trees.size(); i += down) {
            String line = trees.get(i);
            if (line.charAt(pos) == '#') ++treeCounter;
            pos += right;
            pos %= line.length();
        }
        return treeCounter;
    }

}
