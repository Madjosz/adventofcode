package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day19Test {

    @Test
    void day19() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 19);
        Day19 day19 = new Day19(lines);

        assertEquals(213, day19.a1());
        assertEquals(325, day19.a2());
    }

    @Test
    void day19_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 19, "test1");
        Day19 day19 = new Day19(lines);
        assertEquals(2, day19.a1());

        lines = AdventOfCodeUtil.readLines(2020, 19, "test2");
        day19 = new Day19(lines);
        assertEquals(3, day19.a1());
        assertEquals(12, day19.a2());
    }

}
