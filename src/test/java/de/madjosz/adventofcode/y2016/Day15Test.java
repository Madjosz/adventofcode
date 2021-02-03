package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day15Test {

    @Test
    void day15() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 15);
        Day15 day15 = new Day15(lines);

        assertEquals(376777, day15.a1());
        assertEquals(3903937, day15.a2());
    }

    @Test
    void day15_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 15, "test");
        Day15 day15 = new Day15(lines);

        assertEquals(5, day15.a1());
        assertEquals(85, day15.a2());
    }

}
