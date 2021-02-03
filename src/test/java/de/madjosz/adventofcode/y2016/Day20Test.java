package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day20Test {

    @Test
    void day20() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 20);
        Day20 day20 = new Day20(lines);

        assertEquals(32259706L, day20.a1());
        assertEquals(113L, day20.a2());
    }

    @Test
    void day20_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 20, "test");
        Day20 day20 = new Day20(lines);

        assertEquals(3L, day20.a1());
        assertEquals((1L << 32) - 8, day20.a2());
    }

}
