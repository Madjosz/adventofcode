package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day06Test {

    @Test
    void day06() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 6);
        Day06 day06 = new Day06(lines);

        assertEquals("dzqckwsd", day06.a1());
        assertEquals("lragovly", day06.a2());
    }

    @Test
    void day06_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 6, "test");
        Day06 day06 = new Day06(lines);

        assertEquals("easter", day06.a1());
        assertEquals("advent", day06.a2());
    }

}
