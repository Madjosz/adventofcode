package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day10Test {

    @Test
    void day10() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 10);
        Day10 day10 = new Day10(lines.get(0));

        assertEquals(360154, day10.a1());
        assertEquals(5103798, day10.a2());
    }

    @Test
    void day10_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 10, "test");
        Day10 day10 = new Day10(lines.get(0));

        assertEquals("11", day10.lookAndSay(1));
        assertEquals("21", day10.lookAndSay(2));
        assertEquals("1211", day10.lookAndSay(3));
        assertEquals("111221", day10.lookAndSay(4));
        assertEquals("312211", day10.lookAndSay(5));
        assertEquals(82350, day10.a1());
        assertEquals(1166642, day10.a2());
    }

}
