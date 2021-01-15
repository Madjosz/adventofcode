package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day25Test {

    @Test
    void day25() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 25);
        Day25 day25 = new Day25(lines.get(0));

        assertEquals(2650453L, day25.a1());
        assertEquals("50 stars", day25.a2());
    }

    @Test
    void day25_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 25, "test");
        Day25 day25 = new Day25(lines.get(0));

        assertEquals(27995004L, day25.a1());
        assertEquals("50 stars", day25.a2());
    }

}
