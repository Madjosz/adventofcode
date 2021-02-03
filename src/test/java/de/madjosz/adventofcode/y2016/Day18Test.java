package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day18Test {

    @Test
    void day18() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 18);
        Day18 day18 = new Day18(lines.get(0));

        assertEquals(1978, day18.a1());
        assertEquals(20003246, day18.a2());
    }

    @Test
    void day18_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 18, "test");

        Day18 day18 = new Day18(lines.get(0));
        assertEquals(6, day18.a1(3));

        day18 = new Day18(lines.get(1));
        assertEquals(38, day18.a1(10));
    }

}
