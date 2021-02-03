package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day16Test {

    @Test
    void day16() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 16);
        Day16 day16 = new Day16(lines);

        assertEquals("11100110111101110", day16.a1());
        assertEquals("10001101010000101", day16.a2());
    }

    @Test
    void day16_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 16, "test");
        Day16 day16 = new Day16(lines);

        assertEquals("01100", day16.a1(20));
    }

}
