package de.madjosz.adventofcode.y2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day21Test {

    @Test
    void day21() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 21);
        Day21 day21 = new Day21(lines);

        assertEquals(111, day21.a1());
        assertEquals(188, day21.a2());
    }

    @Test
    void day21_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2015, 21, "test");
        Day21 day21 = new Day21(lines);

        assertEquals(105, day21.a1(8));
        assertEquals(225, day21.a2(8));
    }

}
