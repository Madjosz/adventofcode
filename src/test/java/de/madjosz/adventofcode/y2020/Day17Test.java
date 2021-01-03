package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day17Test {

    @Test
    void day17() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 17);
        Day17 day17 = new Day17(lines);

        assertEquals(348, day17.a1());
        assertEquals(2236, day17.a2());
    }

    @Test
    void day17_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 17, "test");
        Day17 day17 = new Day17(lines);

        assertEquals(112, day17.a1());
        assertEquals(848, day17.a2());
    }
}
