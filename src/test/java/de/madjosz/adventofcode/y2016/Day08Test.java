package de.madjosz.adventofcode.y2016;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.madjosz.adventofcode.AdventOfCodeUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class Day08Test {

    @Test
    void day08() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 8);
        Day08 day08 = new Day08(lines);

        assertEquals(128, day08.a1());
        // assertEquals("EOARGPHYAO", day08.a2());
        assertEquals("""
                ####  ##   ##  ###   ##  ###  #  # #   # ##   ##\s\s
                #    #  # #  # #  # #  # #  # #  # #   ##  # #  #\s
                ###  #  # #  # #  # #    #  # ####  # # #  # #  #\s
                #    #  # #### ###  # ## ###  #  #   #  #### #  #\s
                #    #  # #  # # #  #  # #    #  #   #  #  # #  #\s
                ####  ##  #  # #  #  ### #    #  #   #  #  #  ##\s\s""", day08.a2());
    }

    @Test
    void day08_exampleinput() {
        List<String> lines = AdventOfCodeUtil.readLines(2016, 8, "test");
        Day08 day08 = new Day08(lines);

        assertEquals(6, day08.a1(7, 3));
        assertEquals(" #  # #\n# #    \n #     ", day08.a2(7, 3));
    }

}
