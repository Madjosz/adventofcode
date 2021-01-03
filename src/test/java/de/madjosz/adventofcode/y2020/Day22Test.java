package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day22Test {

    @Test
    void day22() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 22);
        Day22 day22 = new Day22(lines);

        assertEquals(30197, day22.a1());
        assertEquals(34031, day22.a2());
    }

    @Test
    void day22_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 22, "test");
        Day22 day22 = new Day22(lines);

        assertEquals(306, day22.a1());
        assertEquals(291, day22.a2());
    }
}
