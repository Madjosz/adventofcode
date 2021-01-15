package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day06Test {

    @Test
    void day06() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 6);
        Day06 day06 = new Day06(lines);

        assertEquals(569999, day06.a1());
        assertEquals(17836115, day06.a2());
    }

    @Test
    void day06_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 6, "test");
        Day06 day06 = new Day06(lines);

        assertEquals(1_000_000 - 1000 - 4, day06.a1());
        assertEquals(1_000_000 + 2 * 1000 - 4, day06.a2());
    }

}
