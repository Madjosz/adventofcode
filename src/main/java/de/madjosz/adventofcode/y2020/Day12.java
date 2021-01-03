package de.madjosz.adventofcode.y2020;

import java.util.List;


public class Day12 {

    private final List<String> actions;

    public Day12(List<String> input) {
        this.actions = input;
    }

    private enum D {

        E, S, W, N;

        D rotateR(int deg) {
            return values()[(ordinal() + deg / 90) % 4];
        }

        D rotateL(int deg) {
            return values()[(4 + ordinal() - deg / 90) % 4];
        }

        void move(int[] pos, int units) {
            switch (this) {
            case E: pos[0] += units; break;
            case S: pos[1] -= units; break;
            case W: pos[0] -= units; break;
            case N: pos[1] += units; break;
            }
        }
    }

    public int a1() {
        D dir = D.E;
        int[] pos = new int[2];
        for (String action : actions) {
            String command = action.substring(0, 1);
            int amount = Integer.parseInt(action.substring(1));
            switch (command) {
            case "F": dir.move(pos, amount);     break;
            case "R": dir = dir.rotateR(amount); break;
            case "L": dir = dir.rotateL(amount); break;
            default:  D.valueOf(command).move(pos, amount);
            }
        }
        return Math.abs(pos[0]) + Math.abs(pos[1]);
    }

    public int a2() {
        int[] ship = new int[2];
        int[] pos = new int[] { 10, 1 };
        for (String action : actions) {
            String command = action.substring(0, 1);
            int amount = Integer.parseInt(action.substring(1));
            switch (command) {
            case "F":
                ship[0] += amount * pos[0];
                ship[1] += amount * pos[1];
                break;
            case "R":
                amount *= -1;
            case "L":
                pos = rotate(pos, amount);
                break;
            default:
                D.valueOf(command).move(pos, amount);
            }
        }
        return Math.abs(ship[0]) + Math.abs(ship[1]);
    }

    private static int[] rotate(int[] pos, int deg) {
        double rad = Math.toRadians(deg);
        int cos = (int) Math.round(Math.cos(rad));
        int sin = (int) Math.round(Math.sin(rad));
        return new int[] { cos * pos[0] - sin * pos[1], sin * pos[0] + cos * pos[1] };
    }

}
