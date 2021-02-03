package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day10Test {

    @Test
    void day10() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 10);
        Day10 day10 = new Day10(lines);

        assertEquals(73, day10.a1());
        assertEquals(3965, day10.a2());
    }

    @Test
    void day10_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 10, "test");
        Day10 day10 = new Day10(lines);

        assertEquals(2, day10.a1(5, 2));
        assertEquals(30, day10.a2());
    }

}
