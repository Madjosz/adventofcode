package de.madjosz.adventofcode.y2020;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.IntStream;


public class Day05 {

    private final List<String> boardingPasses;

    public Day05(List<String> input) {
        this.boardingPasses = input;
    }

    public int a1() {
        return boardingPasses.stream().mapToInt(Day05::getId).max().getAsInt();
    }

    public int a2() {
        SortedSet<Integer> seats = IntStream.range(0, 855).collect(TreeSet::new, Set::add, Set::addAll);
        boardingPasses.stream().mapToInt(Day05::getId).forEach(i -> seats.remove(i));
        return seats.last();
    }

    private static int getId(String seat) {
        return 8 * getRow(seat) + getCol(seat);
    }

    private static int getRow(String seat) {
        return Integer.parseInt(seat.substring(0, 7).replace('F', '0').replace('B', '1'), 2);
    }

    private static int getCol(String seat) {
        return Integer.parseInt(seat.substring(7, 10).replace('L', '0').replace('R', '1'), 2);
    }

}
