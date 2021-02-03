package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day14Test {

    @Test
    void day14() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 14);
        Day14 day14 = new Day14(lines);

        assertEquals(15035, day14.a1());
        assertEquals(19968, day14.a2());
    }

    @Test
    void day14_exampleinput() throws NoSuchAlgorithmException {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 14, "test");
        Day14 day14 = new Day14(lines);

        assertEquals("a107ff634856bb300138cac6568c0f24", day14.repeatedMD5(2016).apply(lines.get(0) + "0"));
        assertEquals(22728, day14.a1());
        assertEquals(22551, day14.a2());
    }

}
