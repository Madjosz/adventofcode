package de.madjosz.adventofcode.y2016;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day15 {

    private final List<int[]> discs;

    private static final Pattern DISC = Pattern.compile("(\\d+) positions; at time=0, it is at position (\\d+)");

    public Day15(List<String> input) {
        this.discs = input.stream()
                .map(DISC::matcher)
                .filter(Matcher::find)
                .map(g -> new int[] { Integer.parseInt(g.group(1)), Integer.parseInt(g.group(2)) })
                .collect(toList());
    }

    public int a1() {
        return calculateWaitingTime(discs);
    }

    private int calculateWaitingTime(List<int[]> allDiscs) {
        int step = 1;
        int t = 0;
        for (int i = 0; i < allDiscs.size(); ++i) {
            ++t;
            int[] disc = allDiscs.get(i);
            while ((disc[1] + t) % disc[0] != 0)
                t += step;
            step *= disc[0];
        }
        return t - allDiscs.size();
    }

    public int a2() {
        List<int[]> moreDiscs = new ArrayList<>(discs);
        moreDiscs.add(new int[] { 11, 0 });
        return calculateWaitingTime(moreDiscs);
    }
}
