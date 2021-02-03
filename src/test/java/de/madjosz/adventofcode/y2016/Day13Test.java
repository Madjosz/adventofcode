package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day13Test {

    @Test
    void day13() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 13);
        Day13 day13 = new Day13(lines);

        assertEquals(96, day13.a1());
        assertEquals(141, day13.a2());
    }

    @Test
    void day13_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 13, "test");
        Day13 day13 = new Day13(lines);

        assertEquals(11, day13.a1(7, 4));
        assertEquals(18, day13.a2(10));
    }

}
