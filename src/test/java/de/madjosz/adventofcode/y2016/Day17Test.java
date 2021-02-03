package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day17Test {

    @Test
    void day17() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 17);
        Day17 day17 = new Day17(lines.get(0));

        assertEquals("RLRDRDUDDR", day17.a1());
        assertEquals(420, day17.a2());
    }

    @Test
    void day17_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 17, "test");

        Day17 day17 = new Day17(lines.get(0));
        assertEquals("DDRRRD", day17.a1());
        assertEquals(370, day17.a2());
        day17 = new Day17(lines.get(1));
        assertEquals("DDUDRLRRUDRD", day17.a1());
        assertEquals(492, day17.a2());
        day17 = new Day17(lines.get(2));
        assertEquals("DRURDRUDDLLDLUURRDULRLDUUDDDRR", day17.a1());
        assertEquals(830, day17.a2());
    }

}
