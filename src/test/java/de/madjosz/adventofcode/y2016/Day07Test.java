package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day07Test {

    @Test
    void day07() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 7);
        Day07 day07 = new Day07(lines);

        assertEquals(115, day07.a1());
        assertEquals(231, day07.a2());
    }

    @Test
    void day07_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 7, "test1");
        Day07 day07 = new Day07(lines);
        assertEquals(2, day07.a1());

        lines = AdventOfCodeUtil.readLines(2016, 7, "test2");
        day07 = new Day07(lines);
        assertEquals(3, day07.a2());
    }

}
