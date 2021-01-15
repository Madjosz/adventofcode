package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day15Test {

    @Test
    void day15() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 15);
        Day15 day15 = new Day15(lines);

        assertEquals(13882464, day15.a1());
        assertEquals(11171160, day15.a2());
    }

    @Test
    void day15_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 15, "test");
        Day15 day15 = new Day15(lines);

        assertEquals(62842880, day15.a1());
        assertEquals(57600000, day15.a2());
    }

}
