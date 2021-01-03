package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day06Test {

    @Test
    void day06() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 6);
        Day06 day06 = new Day06(lines);

        assertEquals(6259, day06.a1());
        assertEquals(3178, day06.a2());
    }

    @Test
    void day06_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 6, "test");
        Day06 day06 = new Day06(lines);

        assertEquals(11, day06.a1());
        assertEquals(6, day06.a2());
    }

}
