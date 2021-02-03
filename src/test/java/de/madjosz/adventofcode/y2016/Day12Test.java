package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day12Test {

    @Test
    void day12() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 12);
        Day12 day12 = new Day12(lines);

        assertEquals(318009, day12.a1());
        assertEquals(9227663, day12.a2());
    }

    @Test
    void day12_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 12, "test");
        Day12 day12 = new Day12(lines);

        assertEquals(42, day12.a1());
        assertEquals(42, day12.a2());
    }

}
