package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day25Test {

    @Test
    void day25() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 25);
        Day25 day25 = new Day25(lines);

        assertEquals(15467093, day25.a1());
        assertEquals(15467093, day25.a1(1));
        assertEquals("we are done", day25.a2());
    }

    @Test
    void day25_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 25, "test");
        Day25 day25 = new Day25(lines);

        assertEquals(14897079, day25.a1());
        assertEquals(14897079, day25.a1(1));
        assertEquals("we are done", day25.a2());
    }
}
