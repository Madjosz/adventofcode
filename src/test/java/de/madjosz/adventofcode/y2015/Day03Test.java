package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day03Test {

    @Test
    void day03() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 3);
        Day03 day03 = new Day03(lines.get(0));

        assertEquals(2565, day03.a1());
        assertEquals(2639, day03.a2());
    }

    @Test
    void day03_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 3, "test");

        Day03 day03 = new Day03(lines.get(0));
        assertEquals(2, day03.a1());
        assertEquals(2, day03.a2());

        day03 = new Day03(lines.get(1));
        assertEquals(2, day03.a1());
        assertEquals(3, day03.a2());

        day03 = new Day03(lines.get(2));
        assertEquals(4, day03.a1());
        assertEquals(3, day03.a2());

        day03 = new Day03(lines.get(3));
        assertEquals(2, day03.a1());
        assertEquals(11, day03.a2());
    }

}
