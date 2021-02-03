package de.madjosz.adventofcode.y2016;

import java.util.List;


public class Day12 {

    private final List<String> input;

    public Day12(List<String> input) {
        this.input = input;
    }

    public int a1() {
        return run(new int[4]);
    }

    private int run(int[] reg) {
        int pos = 0;
        while (pos < input.size()) {
            String[] cmd = input.get(pos).split(" ");
            switch (cmd[0]) {
            case "cpy":
                if (Character.isDigit(cmd[1].charAt(0))) reg[cmd[2].charAt(0) - 'a'] = Integer.parseInt(cmd[1]);
                else reg[cmd[2].charAt(0) - 'a'] = reg[cmd[1].charAt(0) - 'a'];
                break;
            case "inc":
                ++reg[cmd[1].charAt(0) - 'a'];
                break;
            case "dec":
                --reg[cmd[1].charAt(0) - 'a'];
                break;
            case "jnz":
                int value = Character.isDigit(cmd[1].charAt(0)) ? Integer.parseInt(cmd[1])
                        : reg[cmd[1].charAt(0) - 'a'];
                if (value != 0) pos += Integer.parseInt(cmd[2]) - 1;
            }
            ++pos;
        }
        return reg[0];
    }

    public int a2() {
        return run(new int[] { 0, 0, 1, 0 });
    }
}
