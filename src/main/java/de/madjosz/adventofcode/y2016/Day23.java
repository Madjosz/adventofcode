package de.madjosz.adventofcode.y2016;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

import java.util.List;


public class Day23 {

    private final List<String> input;

    public Day23(List<String> input) {
        this.input = input;
    }

    public int a1() {
        return new AssembunnyInterpreter(input).run(new int[] { 7, 0, 0, 0 });
    }

    private static class AssembunnyInterpreter {

        private final List<String[]> instructions;

        AssembunnyInterpreter(List<String> instructions) {
            this.instructions = instructions.stream().map(s -> s.split(" ")).collect(toList());
        }

        private int run(int[] reg) {
            int pos = 0;
            while (pos < instructions.size()) {
                String[] cmd = instructions.get(pos);
                switch (cmd[0]) {
                case "cpy": copy(reg, cmd); break;
                case "inc": if (isRegister(cmd[1])) ++reg[idx(cmd[1])]; break;
                case "dec": if (isRegister(cmd[1])) --reg[idx(cmd[1])]; break;
                case "jnz": pos += jump(reg, pos, cmd); break;
                case "tgl": toggle(reg, pos, cmd); break;
                default: throw new IllegalArgumentException(cmd[0]);
                }
                ++pos;
            }
            return reg[0];
        }

        private void copy(int[] reg, String[] cmd) {
            if (isRegister(cmd[2])) reg[idx(cmd[2])] = getValue(cmd[1], reg);
        }

        private void toggle(int[] reg, int pos, String[] cmd) {
            int idx = pos + getValue(cmd[1], reg);
            if (idx >= 0 && idx < instructions.size()) {
                String[] toggleCmd = instructions.get(idx);
                toggleCmd[0] = toggle(toggleCmd[0]);
            }
        }

        private int jump(int[] reg, int pos, String[] cmd) {
            if (isMultiplication(cmd, pos)) doMultiply(reg, pos);
            else if (isAddition(cmd)) doAdd(reg, pos, cmd);
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

        private boolean isAddition(String[] cmd) {
            return !isRegister(cmd[2]) && getValue(cmd[2], null) == -2;
        }

        private String toggle(String cmd) {
            return switch (cmd) {
            case "inc" -> "dec";
            case "dec", "tgl" -> "inc";
            case "cpy" -> "jnz";
            case "jnz" -> "cpy";
            default -> throw new IllegalArgumentException("Unexpected value: " + cmd);
            };
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

    public int a2() {
        return new AssembunnyInterpreter(input).run(new int[] { 12, 0, 0, 0 });
    }
}
