package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day22Test {

    @Test
    void day22() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 22);
        Day22 day22 = new Day22(lines);

        assertEquals(955, day22.a1());
        assertEquals(246, day22.a2());
    }

    @Test
    void day22_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 22, "test");
        Day22 day22 = new Day22(lines);

        assertEquals(7, day22.a1());
        assertEquals(7, day22.a2());
    }

}
