package de.madjosz.adventofcode.y2020;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;


public class Day08 {

    private final List<String> code;

    public Day08(List<String> input) {
        this.code = input;
    }

    public int a1() {
        int accumulator = 0;
        int pos = 0;
        BitSet visited = new BitSet(code.size());
        while (!visited.get(pos)) {
            visited.set(pos);
            String instruction = code.get(pos);
            switch (instruction.substring(0, 3)) {
            case "jmp":
                pos += Integer.parseInt(instruction.substring(4));
                break;
            case "acc":
                accumulator += Integer.parseInt(instruction.substring(4));
            default:
                ++pos;
            }
        }
        return accumulator;
    }

    public int a2() {
        List<String> modifiedCode = new ArrayList<>(code);
        for (int i = 0; i < code.size(); ++i) {
            String instruction = code.get(i);
            if (instruction.startsWith("acc")) continue;
            modifiedCode.set(i, (instruction.startsWith("nop") ? "jmp" : "nop") + instruction.substring(3));
            try {
                int acc = execute(modifiedCode);
                System.out.println(i + " " + instruction);
                return acc;
            } catch (IllegalStateException e) {}
            modifiedCode.set(i, instruction);
        }
        throw new IllegalStateException();
    }

    private int execute(List<String> modifiedCode) {
        int accumulator = 0;
        int pos = 0;
        BitSet visited = new BitSet(modifiedCode.size() + 1);
        while (pos < modifiedCode.size()) {
            visited.set(pos);
            String instruction = modifiedCode.get(pos);
            switch (instruction.substring(0, 3)) {
            case "jmp":
                pos += Integer.parseInt(instruction.substring(4));
                break;
            case "acc":
                accumulator += Integer.parseInt(instruction.substring(4));
            default:
                ++pos;
            }
            if (visited.get(pos)) throw new IllegalStateException();
        }
        return accumulator;
    }

}
