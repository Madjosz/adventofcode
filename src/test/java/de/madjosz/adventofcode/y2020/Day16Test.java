package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day16Test {

    @Test
    void day16() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 16);
        Day16 day16 = new Day16(lines);

        assertEquals(26053, day16.a1());
        assertEquals(1515506256421L, day16.a2());
    }

    @Test
    void day16_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 16, "test1");
        Day16 day16 = new Day16(lines);
        assertEquals(71, day16.a1());

        lines = AdventOfCodeUtil.readLines(2020, 16, "test2");
        day16 = new Day16(lines);
        assertEquals(11 * 12, day16.a2());
    }

}
