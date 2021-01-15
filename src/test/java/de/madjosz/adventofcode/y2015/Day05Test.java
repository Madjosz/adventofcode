package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day05Test {

    @Test
    void day05() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 5);
        Day05 day05 = new Day05(lines);

        assertEquals(238, day05.a1());
        assertEquals(69, day05.a2());
    }

    @Test
    void day05_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 5, "test1");
        Day05 day05 = new Day05(lines);
        assertEquals(2, day05.a1());

        lines = AdventOfCodeUtil.readLines(2015, 5, "test2");
        day05 = new Day05(lines);
        assertEquals(2, day05.a2());
    }

}
