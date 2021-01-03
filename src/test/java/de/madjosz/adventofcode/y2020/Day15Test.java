package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class Day15Test {

    @Test
    void day15() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 15);
        Day15 day15 = new Day15(lines.get(0));

        assertEquals(421, day15.a1());
        assertEquals(436, day15.a2());
    }

    @CsvSource({ //
            "436, 175594, 0", //
            "1, 2578, 1", //
            "10, 3544142, 2", //
            "27, 261214, 3", //
            "78, 6895259, 4", //
            "438, 18, 5", //
            "1836, 362,6" //
    })
    @ParameterizedTest
    void day15_exampleInput(int expected1, int expected2, int lineIndex) {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 15, "test");

        Day15 day15 = new Day15(lines.get(lineIndex));
        assertEquals(expected1, day15.a1());
        assertEquals(expected2, day15.a2());
    }
}
