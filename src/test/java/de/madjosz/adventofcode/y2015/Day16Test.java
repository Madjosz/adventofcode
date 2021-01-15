package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day16Test {

    @Test
    void day16() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 16);
        Day16 day16 = new Day16(lines);

        assertEquals(213, day16.a1());
        assertEquals(323, day16.a2());
    }

    @Test
    void day16_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 16, "test");
        Day16 day16 = new Day16(lines);

        assertEquals(3, day16.a1());
        assertEquals(2, day16.a2());
    }

}
