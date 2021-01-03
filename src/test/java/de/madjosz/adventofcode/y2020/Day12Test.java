package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day12Test {

    @Test
    void day12() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 12);
        Day12 day12 = new Day12(lines);

        assertEquals(1032, day12.a1());
        assertEquals(156735, day12.a2());
    }

    @Test
    void day12_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 12, "test");
        Day12 day12 = new Day12(lines);

        assertEquals(25, day12.a1());
        assertEquals(286, day12.a2());
    }

}