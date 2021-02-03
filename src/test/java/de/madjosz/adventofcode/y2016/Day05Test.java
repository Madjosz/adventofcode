package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day05Test {

    @Test
    void day05() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 5);
        Day05 day05 = new Day05(lines.get(0));

        assertEquals("2414bc77", day05.a1());
        assertEquals("437e60fc", day05.a2());
    }

    @Test
    void day05_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 5, "test");
        Day05 day05 = new Day05(lines.get(0));

        assertEquals("18f47a30", day05.a1());
        assertEquals("05ace8e3", day05.a2());
    }

}
