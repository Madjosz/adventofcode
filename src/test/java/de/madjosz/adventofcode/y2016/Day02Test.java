package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day02Test {

    @Test
    void day02() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 2);
        Day02 day02 = new Day02(lines);

        assertEquals(33444, day02.a1());
        assertEquals("446A6", day02.a2());
    }

    @Test
    void day02_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 2, "test");
        Day02 day02 = new Day02(lines);

        assertEquals(1985, day02.a1());
        assertEquals("5DB3", day02.a2());
    }

}
