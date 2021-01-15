package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;


class Day07Test {

    @Test
    void day07() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 7);
        Day07 day07 = new Day07(lines);

        assertEquals(16076, day07.a1());
        assertEquals(2797, day07.a2());
    }

    @Test
    void day07_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 7, "test");
        Day07 day07 = new Day07(lines);

        assertEquals(Map.of("d", 72, "e", 507, "f", 492, "g", 114, "h", 65412, "i", 65079, "x", 123, "y", 456),
                day07.wireThem());
    }

}
