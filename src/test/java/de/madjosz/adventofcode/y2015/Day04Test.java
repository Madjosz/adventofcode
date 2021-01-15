package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day04Test {

    @Test
    void day04() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 4);
        Day04 day04 = new Day04(lines.get(0));

        assertEquals(254575, day04.a1());
        assertEquals(1038736, day04.a2());
    }

    @Test
    void day04_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 4, "test");

        Day04 day04 = new Day04(lines.get(0));
        assertEquals(609043, day04.a1());
        assertEquals(6742839, day04.a2());

        day04 = new Day04(lines.get(1));
        assertEquals(1048970, day04.a1());
        assertEquals(5714438, day04.a2());
    }

}
