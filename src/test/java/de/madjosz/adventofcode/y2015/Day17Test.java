package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day17Test {

    @Test
    void day17() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 17);
        Day17 day17 = new Day17(lines);

        assertEquals(4372, day17.a1());
        assertEquals(4, day17.a2());
    }

    @Test
    void day17_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 17, "test");
        Day17 day17 = new Day17(lines);

        assertEquals(4, day17.a1(25));
        assertEquals(3, day17.a2(25));
    }

}
