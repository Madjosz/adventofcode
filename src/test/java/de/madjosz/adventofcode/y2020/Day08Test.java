package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day08Test {

    @Test
    void day08() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 8);
        Day08 day08 = new Day08(lines);

        assertEquals(1709, day08.a1());
        assertEquals(1976, day08.a2());
    }

    @Test
    void day08_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 8, "test");
        Day08 day08 = new Day08(lines);

        assertEquals(5, day08.a1());
        assertEquals(8, day08.a2());
    }

}
