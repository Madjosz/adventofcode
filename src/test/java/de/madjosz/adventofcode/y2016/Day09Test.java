package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day09Test {

    @Test
    void day09() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 9);
        Day09 day09 = new Day09(lines);

        assertEquals(70186, day09.a1());
        assertEquals(10915059201L, day09.a2());
    }

    @Test
    void day09_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 9, "test1");
        Day09 day09 = new Day09(lines);
        assertEquals(6 + 7 + 9 + 11 + 6 + 18, day09.a1());

        lines = AdventOfCodeUtil.readLines(2016, 9, "test2");
        day09 = new Day09(lines);
        assertEquals(9 + 20 + 241920 + 445, day09.a2());
    }

}
