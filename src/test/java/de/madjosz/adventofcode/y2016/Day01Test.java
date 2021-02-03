package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day01Test {

    @Test
    void day01() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 1);
        Day01 day01 = new Day01(lines.get(0));

        assertEquals(287, day01.a1());
        assertEquals(133, day01.a2());
    }

    @Test
    void day01_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 1, "test");

        Day01 day01 = new Day01(lines.get(0));
        assertEquals(5, day01.a1());

        day01 = new Day01(lines.get(1));
        assertEquals(2, day01.a1());

        day01 = new Day01(lines.get(2));
        assertEquals(12, day01.a1());

        day01 = new Day01(lines.get(3));
        assertEquals(4, day01.a2());
    }

}
