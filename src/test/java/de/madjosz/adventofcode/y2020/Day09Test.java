package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day09Test {

    @Test
    void day09() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 9);
        Day09 day09 = new Day09(lines, 25);

        assertEquals(138879426, day09.a1());
        assertEquals(23761694, day09.a2());
    }

    @Test
    void day09_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 9, "test");
        Day09 day09 = new Day09(lines, 5);

        assertEquals(127, day09.a1());
        assertEquals(62, day09.a2());
    }

}
