package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day02Test {

    @Test
    void day02() {
        List<String> input = AdventOfCodeUtil.readLines(2020, 2);
        Day02 day02 = new Day02(input);

        assertEquals(591, day02.a1());
        assertEquals(335, day02.a2());
    }

    @Test
    void day02_exampleinput() {
        List<String> input = AdventOfCodeUtil.readLines(2020, 2, "test");
        Day02 day02 = new Day02(input);

        assertEquals(2, day02.a1());
        assertEquals(1, day02.a2());
    }

}
