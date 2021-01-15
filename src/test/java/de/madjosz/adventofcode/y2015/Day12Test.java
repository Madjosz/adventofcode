package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day12Test {

    @Test
    void day12() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 12);
        Day12 day12 = new Day12(lines.get(0));

        assertEquals(119433, day12.a1());
        assertEquals(68466, day12.a2());
    }

    @Test
    void day12_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 12, "test");

        Day12 day12 = new Day12(lines.get(0));
        assertEquals(6, day12.a1());
        assertEquals(6, day12.a2());
        day12 = new Day12(lines.get(1));
        assertEquals(6, day12.a1());
        day12 = new Day12(lines.get(2));
        assertEquals(3, day12.a1());
        day12 = new Day12(lines.get(3));
        assertEquals(3, day12.a1());
        day12 = new Day12(lines.get(4));
        assertEquals(0, day12.a1());
        day12 = new Day12(lines.get(5));
        assertEquals(0, day12.a1());
        day12 = new Day12(lines.get(6));
        assertEquals(0, day12.a1());
        day12 = new Day12(lines.get(7));
        assertEquals(0, day12.a1());
        day12 = new Day12(lines.get(8));
        assertEquals(4, day12.a2());
        day12 = new Day12(lines.get(9));
        assertEquals(0, day12.a2());
        day12 = new Day12(lines.get(10));
        assertEquals(6, day12.a2());
    }

}
