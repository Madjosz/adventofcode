package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day18Test {

    @Test
    void day18() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 18);
        Day18 day18 = new Day18(lines);

        assertEquals(1061, day18.a1());
        assertEquals(1006, day18.a2());
    }

    @Test
    void day18_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 18, "test");
        Day18 day18 = new Day18(lines);

        assertEquals(4, day18.a1(4));
        assertEquals(17, day18.a2(5));
    }

}
