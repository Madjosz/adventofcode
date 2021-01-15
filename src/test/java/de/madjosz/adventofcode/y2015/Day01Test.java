package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day01Test {

    @Test
    void day01() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 1);
        Day01 day01 = new Day01(lines.get(0));

        assertEquals(280, day01.a1());
        assertEquals(1797, day01.a2());
    }

    @Test
    void day01_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 1, "test");

        assertEquals(0, new Day01(lines.get(0)).a1());
        assertEquals(0, new Day01(lines.get(1)).a1());
        assertEquals(3, new Day01(lines.get(2)).a1());
        assertEquals(3, new Day01(lines.get(3)).a1());
        assertEquals(3, new Day01(lines.get(4)).a1());
        assertEquals(-1, new Day01(lines.get(5)).a1());
        assertEquals(-1, new Day01(lines.get(6)).a1());
        assertEquals(-3, new Day01(lines.get(7)).a1());
        assertEquals(-3, new Day01(lines.get(8)).a1());

        assertEquals(1, new Day01(lines.get(4)).a2());
        assertEquals(3, new Day01(lines.get(5)).a2());
    }

}
