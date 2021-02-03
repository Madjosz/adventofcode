package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day19Test {

    @Test
    void day19() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 19);
        Day19 day19 = new Day19(lines);

        assertEquals(1815603, day19.a1());
        assertEquals(1410630, day19.a2());
    }

    @Test
    void day19_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 19, "test");
        Day19 day19 = new Day19(lines);

        assertEquals(3, day19.a1());
        assertEquals(2, day19.a2());
    }

}
