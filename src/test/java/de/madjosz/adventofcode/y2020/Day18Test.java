package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day18Test {

    @Test
    void day18() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 18);
        Day18 day18 = new Day18(lines);

        assertEquals(53660285675207L, day18.a1());
        assertEquals(141993988282687L, day18.a2());
    }

    @Test
    void day18_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 18, "test");
        Day18 day18 = new Day18(lines);

        assertEquals(71 + 51 + 26 + 437 + 12240 + 13632, day18.a1());
        assertEquals(231 + 51 + 46 + 1445 + 669060 + 23340, day18.a2());
    }
}
