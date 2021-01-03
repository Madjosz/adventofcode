package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day07Test {

    @Test
    void day07() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 7);
        Day07 day07 = new Day07(lines);

        assertEquals(148, day07.a1());
        assertEquals(24867, day07.a2());
    }

    @Test
    void day07_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 7, "test");
        Day07 day07 = new Day07(lines);

        assertEquals(4, day07.a1());
        assertEquals(32, day07.a2());

        lines = AdventOfCodeUtil.readLines(2020, 7, "test2");
        day07 = new Day07(lines);
        assertEquals(126, day07.a2());
    }

}
