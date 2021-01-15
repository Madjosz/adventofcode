package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day14Test {

    @Test
    void day14() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 14);
        Day14 day14 = new Day14(lines);

        assertEquals(2640, day14.a1());
        assertEquals(1102, day14.a2());
    }

    @Test
    void day14_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 14, "test");
        Day14 day14 = new Day14(lines);

        assertEquals(1120, day14.a1(1000));
        assertEquals(689, day14.a2(1000));
    }

}
