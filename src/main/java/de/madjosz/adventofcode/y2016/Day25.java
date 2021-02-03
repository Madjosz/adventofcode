package de.madjosz.adventofcode.y2016;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

import java.util.List;


public class Day25 {

    private final List<String> input;

    public Day25(List<String> input) {
        this.input = input;
    }

    public int a1() {
        for (int i = 0; i < Integer.MAX_VALUE; ++i)
            if (new AssembunnyInterpreter(input).checkClockSeries(new int[] { i, 0, 0, 0 })) return i;
        throw new IllegalArgumentException();
    }

    private static class AssembunnyInterpreter {

        private final List<String[]> instructions;

        AssembunnyInterpreter(List<String> instructions) {
            this.instructions = instructions.stream().map(s -> s.split(" ")).collect(toList());
        }

        private boolean checkClockSeries(int[] reg) {
            int lastOut = 1;
            int outCount = 0;
            int pos = 0;
            while (pos < instructions.size()) {
                String[] cmd = instructions.get(pos);
                switch (cmd[0]) {
                case "cpy":
                    copy(reg, cmd);
                    break;
                case "inc":
                    if (isRegister(cmd[1])) ++reg[idx(cmd[1])];
                    break;
                case "dec":
                    if (isRegister(cmd[1])) --reg[idx(cmd[1])];
                    break;
                case "jnz":
                    pos += jump(reg, pos, cmd);
                    break;
                case "out":
                    int out = getValue(cmd[1], reg);
                    if ((lastOut == 1 && out == 0) || (lastOut == 0 && out == 1)) {
                        lastOut = out;
                        if (++outCount > 10) return true;
                    } else return false;
                    break;
                default:
                    throw new IllegalArgumentException(cmd[0]);
                }
                ++pos;
            }
            return false;
        }

        private void copy(int[] reg, String[] cmd) {
            if (isRegister(cmd[2])) reg[idx(cmd[2])] = getValue(cmd[1], reg);
        }

        private int jump(int[] reg, int pos, String[] cmd) {
            if (isMultiplication(cmd, pos)) doMultiply(reg, pos);
            else if (isAddition(cmd, pos)) doAdd(reg, pos, cmd);
            else {
                int value = getValue(cmd[1], reg);
                if (value != 0) return getValue(cmd[2], reg) - 1;
            }
            return 0;
        }

        private void doAdd(int[] reg, int pos, String[] cmd) {
            int loopReg = idx(cmd[1]);
            int reg1 = idx(instructions.get(pos - 2)[1]);
            int reg2 = idx(instructions.get(pos - 1)[1]);
            reg[loopReg == reg1 ? reg2 : reg1] += Math.abs(reg[loopReg]);
            reg[loopReg] = 0;
        }

        private void doMultiply(int[] reg, int pos) {
            copy(reg, instructions.get(pos - 5));
            int loopReg = idx(instructions.get(pos - 2)[1]);
            int reg1 = idx(instructions.get(pos - 1)[1]);
            int reg2 = idx(instructions.get(pos - 3)[1]);
            int reg3 = idx(instructions.get(pos - 4)[1]);
            reg[loopReg == reg2 ? reg3 : reg2] += Math.abs(reg[loopReg] * reg[reg1]);
            reg[loopReg] = 0;
            reg[reg1] = 0;
        }

        private boolean isMultiplication(String[] cmd, int pos) {
            return !isRegister(cmd[2]) && getValue(cmd[2], null) == -5 && "cpy".equals(instructions.get(pos - 5)[1]);
        }

        private boolean isAddition(String[] cmd, int pos) {
            return !isRegister(cmd[2]) && getValue(cmd[2], null) == -2 && "inc".equals(instructions.get(pos - 2)[1])
                    && "dec".equals(instructions.get(pos - 1)[1]);
        }

        private static boolean isRegister(String cmd) {
            return Character.isAlphabetic(cmd.charAt(0));
        }

        private static int idx(String cmd) {
            return cmd.charAt(0) - 'a';
        }

        private static int getValue(String cmd, int[] reg) {
            return isRegister(cmd) ? reg[idx(cmd)] : parseInt(cmd);
        }

    }

    public String a2() {
        return "50 stars";
    }
}
