package de.madjosz.adventofcode.y2016;

import java.util.Arrays;
import java.util.List;


public class Day21 {

    private final List<String> operations;

    public Day21(List<String> input) {
        this.operations = input;
    }

    public String a1() {
        return a1("abcdefgh");
    }

    String a1(String password) {
        Scrambler scrambler = new Scrambler(password);
        for (String op : operations)
            operate(scrambler, op);
        return scrambler.toString();
    }

    private void operate(Scrambler scrambler, String op) {
        switch (op.substring(0, 8)) {
        case "swap pos":
            scrambler.swapPosition(op);
            break;
        case "swap let":
            scrambler.swapLetter(op);
            break;
        case "rotate l":
            scrambler.rotateLeft(op);
            break;
        case "rotate r":
            scrambler.rotateRight(op);
            break;
        case "rotate b":
            scrambler.rotatePosition(op);
            break;
        case "reverse ":
            scrambler.reverse(op);
            break;
        case "move pos":
            scrambler.move(op);
            break;
        default:
            throw new IllegalArgumentException();
        }
    }

    private static class Scrambler {

        private final char[] chars;

        public Scrambler(String chars) {
            this.chars = chars.toCharArray();
        }

        void swapPosition(String op) {
            int x = op.charAt(14) - '0';
            int y = op.charAt(30) - '0';
            swap(x, y);
        }

        void swapLetter(String op) {
            int x = indexOf(op.charAt(12));
            int y = indexOf(op.charAt(26));
            swap(x, y);
        }

        void rotateLeft(String op) {
            rotateRight('0' - op.charAt(12));
        }

        void rotateRight(String op) {
            rotateRight(op.charAt(13) - '0');
        }

        void rotatePosition(String op) {
            rotatePosition(indexOf(op.charAt(35)));
        }

        protected void rotatePosition(int pos) {
            int idx = pos + (pos > 3 ? 2 : 1);
            rotateRight(idx);
        }

        protected void rotateRight(int steps) {
            steps %= chars.length;
            if (steps == 0) return;
            steps += steps < 0 ? chars.length : 0;
            char[] tmp = Arrays.copyOfRange(chars, chars.length - steps, chars.length);
            System.arraycopy(chars, 0, chars, steps, chars.length - steps);
            System.arraycopy(tmp, 0, chars, 0, steps);
        }

        void reverse(String op) {
            int x = op.charAt(18) - '0';
            int y = op.charAt(28) - '0';
            for (int i = 0; i < (y - x + 1) >> 1; ++i)
                swap(x + i, y - i);
        }

        void move(String op) {
            int x = op.charAt(14) - '0';
            int y = op.charAt(28) - '0';
            move(x, y);
        }

        protected void move(int x, int y) {
            char tmp = chars[x];
            if (x < y) System.arraycopy(chars, x + 1, chars, x, y - x);
            else System.arraycopy(chars, y, chars, y + 1, x - y);
            chars[y] = tmp;
        }

        private int indexOf(char needle) {
            for (int i = 0; i < chars.length; ++i)
                if (needle == chars[i]) return i;
            return -1;
        }

        private void swap(int x, int y) {
            char tmp = chars[x];
            chars[x] = chars[y];
            chars[y] = tmp;
        }

        @Override
        public String toString() {
            return String.valueOf(chars);
        }
    }

    public String a2() {
        return a2("fbgdceah");
    }

    String a2(String password) {
        Scrambler scrambler = new Unscrambler(password);
        for (int i = operations.size() - 1; i >= 0; --i)
            operate(scrambler, operations.get(i));
        return scrambler.toString();
    }

    private static class Unscrambler extends Scrambler {

        public Unscrambler(String chars) {
            super(chars);
        }

        @Override
        protected void rotatePosition(int pos) {
            if (pos % 2 == 1) rotateRight((pos + 1) >> 1);
            else if (pos == 0) rotateRight(1);
            else rotateRight(5 + (pos >> 1));
        }

        @Override
        protected void rotateRight(int steps) {
            super.rotateRight(-steps);
        }

        @Override
        protected void move(int x, int y) {
            super.move(y, x);
        }

    }
}
