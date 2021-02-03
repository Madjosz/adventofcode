package de.madjosz.adventofcode.y2016;

import static de.madjosz.adventofcode.data.Coord.DIRECTIONS;

import de.madjosz.adventofcode.data.Coord;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class Day13 {

    private final int favorite;

    public Day13(List<String> input) {
        this.favorite = Integer.parseInt(input.get(0));
    }

    public int a1() {
        return a1(31, 39);
    }

    private boolean isWall(int x, int y) {
        return Integer.bitCount(x * x + 3 * x + 2 * x * y + y + y * y + favorite) % 2 != 0;
    }

    private boolean isWall(Coord coord) {
        return isWall(coord.x(), coord.y());
    }

    private record Step(Coord coord, int steps) {}

    int a1(int goalX, int goalY) {
        Coord start = new Coord(1, 1);
        Set<Coord> seen = new HashSet<>();
        seen.add(start);
        Queue<Step> todo = new ArrayDeque<>();
        todo.offer(new Step(start, 0));

        while (!todo.isEmpty()) {
            Step currentStep = todo.poll();
            Coord pos = currentStep.coord();
            if (pos.x() == goalX && pos.y() == goalY) return currentStep.steps();
            for (int[] dir : DIRECTIONS) {
                Coord nextPos = pos.add(dir);
                if (nextPos.x() >= 0 && nextPos.y() >= 0 && seen.add(nextPos) && !isWall(nextPos)) {
                    todo.offer(new Step(nextPos, currentStep.steps() + 1));
                }
            }
        }
        throw new IllegalStateException();
    }

    public int a2() {
        return a2(50);
    }

    int a2(int maxSteps) {
        Coord start = new Coord(1, 1);
        Set<Coord> seen = new HashSet<>();
        seen.add(start);
        Queue<Step> todo = new ArrayDeque<>();
        todo.offer(new Step(start, 0));

        while (!todo.isEmpty()) {
            Step currentStep = todo.poll();
            if (currentStep.steps() >= maxSteps) continue;

            Coord pos = currentStep.coord();
            for (int[] dir : DIRECTIONS) {
                Coord nextPos = pos.add(dir);
                if (nextPos.x() >= 0 && nextPos.y() >= 0 && !isWall(nextPos) && seen.add(nextPos)) {
                    todo.offer(new Step(nextPos, currentStep.steps() + 1));
                }
            }
        }
        return seen.size();
    }
}
