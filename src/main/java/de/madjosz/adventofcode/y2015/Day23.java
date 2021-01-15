package de.madjosz.adventofcode.y2015;

import static java.util.stream.Collectors.toList;

import java.util.List;


public class Day23 {

    private final List<Instruction> instructions;

    public Day23(List<String> input) {
        this.instructions = input.stream().map(Instruction::parse).collect(toList());
    }

    public long a1() {
        return a1("b");
    }

    long a1(String register) {
        long[] registers = new long[2];
        execute(registers);
        return registers[parseRegister(register)];
    }

    private void execute(long[] registers) {
        int pos = 0;
        while (pos >= 0 && pos < instructions.size()) {
            Instruction instruction = instructions.get(pos);
            instruction.operate(registers);
            pos += instruction.getOffset();
        }
    }

    private static abstract class Instruction {

        protected final int reg;
        private final int off;

        protected Instruction(int reg, int off) {
            this.reg = reg;
            this.off = off;
        }

        public void operate(long[] registers) {}

        public int getOffset() {
            return off;
        }

        public static Instruction parse(String line) {
            String[] parts = line.split(" ", 2);
            switch (parts[0]) {
            case "hlf":
                return new Half(parseRegister(parts[1]));
            case "tpl":
                return new Triple(parseRegister(parts[1]));
            case "inc":
                return new Increment(parseRegister(parts[1]));
            case "jmp":
                return new Jump(Integer.parseInt(parts[1]));
            case "jie":
                String[] p = parts[1].split(", ");
                return new JumpIfEven(parseRegister(p[0]), Integer.parseInt(p[1]));
            case "jio":
                String[] p2 = parts[1].split(", ");
                return new JumpIfOne(parseRegister(p2[0]), Integer.parseInt(p2[1]));
            default:
                throw new IllegalArgumentException(line);
            }
        }

    }

    private static int parseRegister(String reg) {
        return reg.charAt(0) - 'a';
    }

    private static class Half extends Instruction {

        public Half(int register) {
            super(register, 1);
        }

        @Override
        public void operate(long[] registers) {
            registers[reg] /= 2;
        }
    }

    private static class Triple extends Instruction {

        public Triple(int register) {
            super(register, 1);
        }

        @Override
        public void operate(long[] registers) {
            registers[reg] *= 3;
        }
    }

    private static class Increment extends Instruction {

        public Increment(int register) {
            super(register, 1);
        }

        @Override
        public void operate(long[] registers) {
            ++registers[reg];
        }
    }

    private static class Jump extends Instruction {

        public Jump(int offset) {
            super(0, offset);
        }
    }

    private static class JumpIfEven extends Instruction {

        public JumpIfEven(int reg, int off) {
            super(reg, off);
        }

        private boolean isEven;

        @Override
        public void operate(long[] registers) {
            isEven = registers[reg] % 2 == 0;
        }

        @Override
        public int getOffset() {
            return isEven ? super.getOffset() : 1;
        }
    }

    private static class JumpIfOne extends Instruction {

        public JumpIfOne(int reg, int off) {
            super(reg, off);
        }

        private boolean isOne;

        @Override
        public void operate(long[] registers) {
            isOne = registers[reg] == 1;
        }

        @Override
        public int getOffset() {
            return isOne ? super.getOffset() : 1;
        }
    }

    public long a2() {
        return a2("b");
    }

    public long a2(String register) {
        long[] registers = new long[] { 1, 0 };
        execute(registers);
        return registers[parseRegister(register)];
    }
}
