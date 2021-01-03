package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day10Test {

    @Test
    void day10() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 10);
        Day10 day10 = new Day10(lines);

        assertEquals(2059, day10.a1());
        assertEquals(86812553324672L, day10.a2());
    }

    @Test
    void day10_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 10, "test1");
        Day10 day10 = new Day10(lines);

        assertEquals(7 * 5, day10.a1());
        assertEquals(8, day10.a2());

        lines = AdventOfCodeUtil.readLines(2020, 10, "test2");
        day10 = new Day10(lines);

        assertEquals(22 * 10, day10.a1());
        assertEquals(19208, day10.a2());
    }

    @Test
    void malmul_3() throws Exception {
        long[][] m = new long[][] { { 0, 1, 1 }, { 0, 0, 1 }, { 0, 0, 0 } };
        long[][] expected = new long[][] { { 0, 0, 1 }, { 0, 0, 0 }, { 0, 0, 0 } };
        assertTrue(Arrays.deepEquals(expected, Day10.matmul(m, m)));
    }

    @Test
    void malmul_4() throws Exception {
        long[][] m = new long[][] { { 0, 1, 1, 1 }, { 0, 0, 1, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 0 } };
        long[][] expected = new long[][] { { 0, 0, 1, 2 }, { 0, 0, 0, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
        assertTrue(Arrays.deepEquals(expected, Day10.matmul(m, m)));
    }
}
