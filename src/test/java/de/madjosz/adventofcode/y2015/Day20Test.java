package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;


class Day20Test {

    @Test
    void day20() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 20);
        Day20 day20 = new Day20(lines.get(0));

        assertEquals(665280, day20.a1());
        assertEquals(705600, day20.a2());
    }

    @Test
    void day20_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 20, "test");

        Day20 day20 = new Day20(lines.get(0));
        assertEquals(1, day20.a1());
        assertThrows(NoSuchElementException.class, day20::a2);

        day20 = new Day20(lines.get(1));
        assertEquals(2, day20.a1());
        assertEquals(2, day20.a2());

        day20 = new Day20(lines.get(2));
        assertEquals(3, day20.a1());
        assertEquals(3, day20.a2());

        day20 = new Day20(lines.get(3));
        assertEquals(4, day20.a1());
        assertEquals(4, day20.a2());

        day20 = new Day20(lines.get(4));
        assertEquals(4, day20.a1());
        assertEquals(4, day20.a2());

        day20 = new Day20(lines.get(5));
        assertEquals(6, day20.a1());
        assertEquals(6, day20.a2());

        day20 = new Day20(lines.get(6));
        assertEquals(6, day20.a1());
        assertEquals(6, day20.a2());

        day20 = new Day20(lines.get(7));
        assertEquals(8, day20.a1());
        assertEquals(8, day20.a2());

        day20 = new Day20(lines.get(8));
        assertEquals(8, day20.a1());
        assertEquals(6, day20.a2());
    }

}
