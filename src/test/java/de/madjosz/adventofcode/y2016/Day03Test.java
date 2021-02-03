package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day03Test {

    @Test
    void day03() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 3);
        Day03 day03 = new Day03(lines);

        assertEquals(1032, day03.a1());
        assertEquals(1838, day03.a2());
    }

    @Test
    void day03_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 3, "test");
        Day03 day03 = new Day03(lines);

        assertEquals(2, day03.a1());
        assertEquals(1, day03.a2());
    }

}
