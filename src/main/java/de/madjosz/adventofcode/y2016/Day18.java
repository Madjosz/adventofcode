package de.madjosz.adventofcode.y2016;

import java.util.BitSet;


public class Day18 {

    private final BitSet traps = new BitSet();
    private final int width;
    private static final int RULE90 = 0b01011010;

    public Day18(String input) {
        for (int i = 0; i < input.length(); ++i)
            this.traps.set(i, input.charAt(i) == '^');
        this.width = input.length();
    }

    public int a1() {
        return a1(40);
    }

    int a1(int rows) {
        int numberTraps = traps.cardinality();
        BitSet currentRow = (BitSet) traps.clone();
        for (int i = 1; i < rows; ++i) {
            currentRow = determineTraps(currentRow);
            numberTraps += currentRow.cardinality();
        }
        return width * rows - numberTraps;
    }

    private BitSet determineTraps(BitSet currentRow) {
        BitSet nextRow = new BitSet();
        int currentTile = currentRow.get(0) ? 1 : 0;
        for (int i = 0; i < width; ++i) {
            currentTile <<= 1;
            currentTile += currentRow.get(i + 1) ? 1 : 0;
            currentTile &= 0b111;
            if ((RULE90 & (1 << currentTile)) != 0) nextRow.set(i);
        }
        return nextRow;
    }

    public int a2() {
        return a1(400000);
    }
}
