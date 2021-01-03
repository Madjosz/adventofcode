package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day24Test {

    @Test
    void day24() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 24);
        Day24 day24 = new Day24(lines);

        assertEquals(230, day24.a1());
        assertEquals(3565, day24.a2());
    }

    @Test
    void day24_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 24, "test");
        Day24 day24 = new Day24(lines);

        assertEquals(10, day24.a1());
        assertEquals(2208, day24.a2());
    }
}
