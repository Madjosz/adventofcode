package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day03Test {

    @Test
    void day03() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 3);
        Day03 day03 = new Day03(lines);

        assertEquals(252, day03.a1());
        assertEquals(2608962048L, day03.a2());
    }

    @Test
    void day03_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 3, "test");
        Day03 day03 = new Day03(lines);

        assertEquals(7, day03.a1());
        assertEquals(336L, day03.a2());
    }

}
