package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day13Test {

    @Test
    void day13() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 13);
        Day13 day13 = new Day13(lines);

        assertEquals(733, day13.a1());
        assertEquals(725, day13.a2());
    }

    @Test
    void day13_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 13, "test");
        Day13 day13 = new Day13(lines);

        assertEquals(330, day13.a1());
        assertEquals(286, day13.a2());
    }

}
