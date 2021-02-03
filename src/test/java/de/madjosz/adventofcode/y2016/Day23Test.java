package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day23Test {

    @Test
    void day23() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 23);
        Day23 day23 = new Day23(lines);

        assertEquals(11500, day23.a1());
        assertEquals(479008060, day23.a2());
    }

    @Test
    void day23_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 23, "test");
        Day23 day23 = new Day23(lines);

        assertEquals(3, day23.a1());
        assertEquals(3, day23.a2());
    }

}
