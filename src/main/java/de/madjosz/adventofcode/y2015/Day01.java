package de.madjosz.adventofcode.y2015;

public class Day01 {

    private final String input;

    public Day01(String input) {
        this.input = input;
    }

    public int a1() {
        return input.chars().map(c -> c == '(' ? 1 : -1).sum();
    }

    public int a2() {
        int sum = 0;
        for (int i = 0; i < input.length(); ++i) {
            sum += input.charAt(i) == '(' ? 1 : -1;
            if (sum < 0) return i + 1;
        }
        throw new IllegalArgumentException();
    }
}
