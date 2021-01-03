package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day21Test {

    @Test
    void day21() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 21);
        Day21 day21 = new Day21(lines);

        assertEquals(2485, day21.a1());
        assertEquals("bqkndvb,zmb,bmrmhm,snhrpv,vflms,bqtvr,qzkjrtl,rkkrx", day21.a2());
    }

    @Test
    void day21_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 21, "test");
        Day21 day21 = new Day21(lines);

        assertEquals(5, day21.a1());
        assertEquals("mxmxvkd,sqjhc,fvjkl", day21.a2());
    }
}
