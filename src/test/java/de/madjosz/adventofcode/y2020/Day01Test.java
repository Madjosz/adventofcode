package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day01Test {

    @Test
    void day01() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 1);
        Day01 day01 = new Day01(lines);

        assertEquals(926464, day01.a1());
        assertEquals(65656536, day01.a2());
    }

    @Test
    void day01_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 1, "test");
        Day01 day01 = new Day01(lines);

        assertEquals(514579, day01.a1());
        assertEquals(241861950, day01.a2());
    }

}
