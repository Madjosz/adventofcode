package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day11Test {

    @Test
    void day11() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 11);
        Day11 day11 = new Day11(lines.get(0));

        assertEquals("cqjxxyzz", day11.a1());
        assertEquals("cqkaabcc", day11.a2());
    }

    @Test
    void day11_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 11, "test");

        Day11 day11 = new Day11(lines.get(0));
        assertEquals("abcdffaa", day11.a1());
        assertEquals("abcdffbb", day11.a2());

        day11 = new Day11(lines.get(1));
        assertEquals("ghjaabcc", day11.a1());
        assertEquals("ghjbbcdd", day11.a2());
    }

}
