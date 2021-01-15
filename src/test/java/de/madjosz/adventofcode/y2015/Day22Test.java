package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day22Test {

    @Test
    void day22() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 22);
        Day22 day22 = new Day22(lines);

        assertEquals(1269, day22.a1());
        assertEquals(1309, day22.a2());
    }

    @Test
    void day22_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 22, "test1");
        Day22 day22 = new Day22(lines);
        assertEquals(226, day22.a1(10, 250));
        assertEquals(Integer.MAX_VALUE, day22.a2(10, 250));

        lines = AdventOfCodeUtil.readLines(2015, 22, "test2");
        day22 = new Day22(lines);
        assertEquals(641, day22.a1(10, 250));
        assertEquals(Integer.MAX_VALUE, day22.a2(10, 250));
    }

}
