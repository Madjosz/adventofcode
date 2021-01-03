package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day04Test {

    @Test
    void day04() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 4);
        Day04 day04 = new Day04(lines);

        assertEquals(260, day04.a1());
        assertEquals(153, day04.a2());
    }

    @Test
    void day04_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 4, "test1");
        Day04 day04 = new Day04(lines);
        assertEquals(2, day04.a1());

        lines = AdventOfCodeUtil.readLines(2020, 4, "test2");
        day04 = new Day04(lines);
        assertEquals(4, day04.a2());
    }
}
