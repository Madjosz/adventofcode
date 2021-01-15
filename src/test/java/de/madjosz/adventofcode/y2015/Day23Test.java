package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day23Test {

    @Test
    void day23() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 23);
        Day23 day23 = new Day23(lines);

        assertEquals(170, day23.a1());
        assertEquals(247, day23.a2());
    }

    @Test
    void day23_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 23, "test");
        Day23 day23 = new Day23(lines);

        assertEquals(2, day23.a1("a"));
        assertEquals(7, day23.a2("a"));
    }

}
