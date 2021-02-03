package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day11Test {

    @Test
    void day11() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 11);
        Day11 day11 = new Day11(lines);

        assertEquals(37, day11.a1());
        assertEquals(61, day11.a2());
    }

    @Test
    void day11_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 11, "test");
        Day11 day11 = new Day11(lines);

        assertEquals(9, day11.a1());
        assertEquals(33, day11.a2());
    }

}
