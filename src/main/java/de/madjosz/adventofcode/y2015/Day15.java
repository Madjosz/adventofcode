package de.madjosz.adventofcode.y2015;

import java.util.List;
import java.util.function.ToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;


public class Day15 {

    private final int[][] ingredients;

    private static final Pattern PROPERTIES = Pattern
            .compile("capacity (-?\\d+), durability (-?\\d+), flavor (-?\\d+), texture (-?\\d+), calories (-?\\d+)");

    public Day15(List<String> input) {
        this.ingredients = input.stream()
                .map(PROPERTIES::matcher)
                .filter(Matcher::find)
                .map(m -> IntStream.rangeClosed(1, 5).mapToObj(m::group).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);
    }

    public int a1() {
        return getMaxScore(0, new int[ingredients.length], 100, this::getTotalScore);
    }

    private int getMaxScore(int i, int[] params, int remaining, ToIntFunction<int[]> scoring) {
        if (i == params.length) return scoring.applyAsInt(params);
        int maxScore = 0;
        for (int j = 0; j <= remaining; ++j) {
            params[i] = j;
            maxScore = Math.max(maxScore, getMaxScore(i + 1, params, remaining - j, scoring));
        }
        return maxScore;
    }

    private int getTotalScore(int[] amounts) {
        int prod = 1;
        for (int i = 0; i < ingredients[0].length - 1; ++i) {
            int sum = 0;
            for (int a = 0; a < amounts.length; ++a)
                sum += amounts[a] * ingredients[a][i];
            if (sum <= 0) return 0;
            prod *= sum;
        }
        return prod;
    }

    public int a2() {
        return getMaxScore(0, new int[ingredients.length], 100, this::getTotalScoreWhen500Calories);
    }

    private int getTotalScoreWhen500Calories(int[] amounts) {
        int calories = IntStream.range(0, amounts.length).map(i -> amounts[i] * ingredients[i][4]).sum();
        return calories == 500 ? getTotalScore(amounts) : 0;
    }
}
