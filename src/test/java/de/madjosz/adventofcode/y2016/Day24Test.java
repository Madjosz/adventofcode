package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day24Test {

    @Test
    void day24() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 24);
        Day24 day24 = new Day24(lines);

        assertEquals(412, day24.a1());
        assertEquals(664, day24.a2());
    }

    @Test
    void day24_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 24, "test");
        Day24 day24 = new Day24(lines);

        assertEquals(14, day24.a1());
        assertEquals(20, day24.a2());
    }

}
