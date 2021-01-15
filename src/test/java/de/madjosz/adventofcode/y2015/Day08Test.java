package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day08Test {

    @Test
    void day08() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 8);
        Day08 day08 = new Day08(lines);

        assertEquals(1371, day08.a1());
        assertEquals(2117, day08.a2());
    }

    @Test
    void day08_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 8, "test");
        Day08 day08 = new Day08(lines);

        assertEquals(12, day08.a1());
        assertEquals(19, day08.a2());
    }

}
