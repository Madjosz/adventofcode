package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


class Day23Test {

    @Test
    @Disabled("this will run for 30 minutes")
    void day23_firstAttempts() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 23);
        Day23 day23 = new Day23(lines);

        assertEquals("65432978", day23._a1());
        assertEquals(287230227046L, day23._a2());
    }

    @Test
    void day23() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 23);
        Day23 day23 = new Day23(lines);

        assertEquals("65432978", day23.a1());
        assertEquals(287230227046L, day23.a2());
    }

    @Test
    void day23_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 23, "test");
        Day23 day23 = new Day23(lines);

        assertEquals("92658374", day23.a1(10));
        assertEquals("67384529", day23.a1());
        assertEquals(149245887792L, day23.a2());
    }
}
