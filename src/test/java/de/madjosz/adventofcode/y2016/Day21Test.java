package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day21Test {

    @Test
    void day21() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 21);
        Day21 day21 = new Day21(lines);

        assertEquals("gfdhebac", day21.a1());
        assertEquals("dhaegfbc", day21.a2());
    }

    @Test
    void day21_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 21, "test");
        Day21 day21 = new Day21(lines);

        assertEquals("decab", day21.a1("abcde"));
        assertEquals("abcde", day21.a2("decab"));
    }
}
