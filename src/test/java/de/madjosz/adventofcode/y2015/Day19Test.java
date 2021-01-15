package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day19Test {

    @Test
    void day19() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 19);
        Day19 day19 = new Day19(lines);

        assertEquals(535, day19.a1());
        assertEquals(212, day19.a2());
    }

    @Test
    void day19_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 19, "test1");
        assertEquals(4, new Day19(lines).a1());
        assertEquals(7, new Day19(lines, "HOHOHO").a1());

        lines = AdventOfCodeUtil.readLines(2015, 19, "test2");
        assertEquals(3, new Day19(lines).a2());
        assertEquals(6, new Day19(lines, "HOHOHO").a2());
    }

}
