package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day04Test {

    @Test
    void day04() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 4);
        Day04 day04 = new Day04(lines);

        assertEquals(361724, day04.a1());
        assertEquals(482, day04.a2());
    }

    @Test
    void day04_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 4, "test");
        Day04 day04 = new Day04(lines);

        assertEquals(1514, day04.a1());
        assertEquals(404, day04.a2("bch"));
    }

}
