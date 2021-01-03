package de.madjosz.adventofcode.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class Day13Test {

    @Test
    void day13() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 13);
        Day13 day13 = new Day13(lines);

        assertEquals(2215, day13.a1());
        assertEquals(1058443396696792L, day13.a2());
    }

    @Test
    void day13_exampleInput() {
        List<String> lines = AdventOfCodeUtil.readLines(2020, 13, "test");
        Day13 day13 = new Day13(lines);

        assertEquals(295, day13.a1());
        assertEquals(1068781L, day13.a2());

    }

    @CsvSource(delimiter = ';', value = { //
            "2; 2,3", //
            "4; 2,x,3", //
            "0; 2,x,x,3", //
            "2; 2,x,x,x,3", //
            "1; x,2", //
            "0; x,x,2", //
            "1; x,x,x,2", //
            "20; 5,7", //
            "5; 5,x,7", //
            // AoC examples
            "3417;       17,x,13,19", //
            "754018;     67,7,59,61", //
            "779210;     67,x,7,59,61", //
            "1261476;    67,7,x,59,61", //
            "1202161486; 1789,37,47,1889" //
    })
    @ParameterizedTest
    void day13_moreExamples(long expected, String input) {
        assertEquals(expected, new Day13(List.of("", input)).a2());
    }
}
