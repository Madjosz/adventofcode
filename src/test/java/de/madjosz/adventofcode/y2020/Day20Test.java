package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day20Test {

    @Test
    void day20() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 20);
        Day20 day20 = new Day20(lines);

        assertEquals(23386616781851L, day20.a1());
        assertEquals(2376, day20.a2());
    }

    @Test
    void day20_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 20, "test");
        Day20 day20 = new Day20(lines);

        assertEquals(20899048083289L, day20.a1());
        assertEquals(273, day20.a2());
    }
}
