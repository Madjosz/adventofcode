package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day14Test {

    @Test
    void day14() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 14);
        Day14 day14 = new Day14(lines);

        assertEquals(7611244640053L, day14.a1());
        assertEquals(3705162613854L, day14.a2());
    }

    @Test
    void day14_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 14, "test1");
        Day14 day14 = new Day14(lines);
        assertEquals(165, day14.a1());

        lines = AdventOfCodeUtil.readLines(2020, 14, "test2");
        day14 = new Day14(lines);
        assertEquals(208, day14.a2());
    }
}
