package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day02Test {

    @Test
    void day02() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 2);
        Day02 day02 = new Day02(lines);

        assertEquals(1586300, day02.a1());
        assertEquals(3737498, day02.a2());
    }

    @Test
    void day02_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 2, "test");
        Day02 day02 = new Day02(lines);

        assertEquals(58 + 43, day02.a1());
        assertEquals(34 + 14, day02.a2());
    }

}
