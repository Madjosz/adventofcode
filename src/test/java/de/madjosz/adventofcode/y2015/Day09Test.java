package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day09Test {

    @Test
    void day09() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 9);
        Day09 day09 = new Day09(lines);

        assertEquals(207, day09.a1());
        assertEquals(804, day09.a2());
    }

    @Test
    void day09_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 9, "test");
        Day09 day09 = new Day09(lines);

        assertEquals(605, day09.a1());
        assertEquals(982, day09.a2());
    }

}
