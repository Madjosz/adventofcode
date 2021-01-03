package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day05Test {

    @Test
    void day05() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 5);
        Day05 day05 = new Day05(lines);

        assertEquals(855, day05.a1());
        assertEquals(552, day05.a2());
    }

    @Test
    void day05_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 5, "test");
        Day05 day05 = new Day05(lines);

        assertEquals(820, day05.a1());
        // this one does not make sense on the test data
        // assertEquals(854, day05.a2());
    }
}
