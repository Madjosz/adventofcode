package de.madjosz.adventofcode.y2016;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day11 {

    private final List<String> input;

    public Day11(List<String> input) {
        this.input = input;
    }

    private record Facility(int positions, int elevator, int moves) {}

    public int a1() {
        Parsed in = parseInput();
        return new Setup(in.facility(), in.size()).moveAllUp();
    }

    private static class Setup {

        private final Facility initial;
        private final Set<Integer> seen = new HashSet<>();
        private final Queue<Facility> queue = new ArrayDeque<>();
        private final int elements;
        private final int finished;

        Setup(Facility initial, int elements) {
            this.initial = initial;
            this.elements = elements;
            this.finished = Integer.MAX_VALUE >> 4 * (7 - elements) + 3;
        }

        private int moveAllUp() {
            seen.add(initial.positions());
            queue.offer(initial);
            while (true) {
                Facility facility = queue.poll();
                if (friedChip(facility)) continue;
                if (isFinished(facility)) return facility.moves();
                moves(facility);
            }
        }

        private boolean isFinished(Facility facility) {
            return facility.positions() == finished;
        }

        private boolean friedChip(Facility facility) {
            int pos = facility.positions();
            int chips = 0;
            int rtgs = 0;
            for (int i = 0; i < elements; ++i) {
                int pair = getElement(pos, i);
                if (pair % 5 == 0) continue; // both on same floor
                chips |= 1 << (pair & 0x3);
                rtgs |= 1 << (pair >> 2);
            }
            return (chips & rtgs) != 0;
        }

        private void moves(Facility facility) {
            int pos = facility.positions();
            int elv = facility.elevator();
            for (int i = 0; i < 2 * elements; ++i) {
                int p = (pos >> 2 * i) & 0x3;
                if (p != elv) continue;
                if (elv != 3) checkMove(facility, i, 1);
                if (elv != 0) checkMove(facility, i, -1);
                for (int j = i + 1; j < 2 * elements; ++j) {
                    int p2 = (pos >> 2 * j) & 0x3;
                    if (p2 != elv) continue;
                    if (elv != 3) checkMove(facility, i, j, 1);
                    if (elv != 0) checkMove(facility, i, j, -1);
                }
            }
        }

        private void checkMove(Facility facility, int i, int dir) {
            int newPos = sort(facility.positions() + (dir << 2 * i), dir);
            int elv = facility.elevator() + dir;
            if (seen.add(newPos + (elv << 4 * elements))) {
                queue.offer(new Facility(newPos, elv, facility.moves() + 1));
            }
        }

        private void checkMove(Facility facility, int i, int j, int dir) {
            int newPos = facility.positions() + (dir << 2 * i);
            newPos += dir << 2 * j;
            newPos = sort(sort(newPos, dir), dir);
            int elv = facility.elevator() + dir;
            if (seen.add(newPos + (elv << 4 * elements))) queue.offer(new Facility(newPos, elv, facility.moves() + 1));
        }

        private int getElement(int positions, int index) {
            return (positions >> 4 * index) & 0xF;
        }

        private int sort(int pos, int dir) {
            if (dir == 1) {
                for (int i = 1; i < elements; ++i)
                    pos = swapIfInverted(pos, i);
                return pos;
            } else {
                for (int i = elements - 1; i > 0; --i)
                    pos = swapIfInverted(pos, i);
                return pos;
            }
        }

        private int swapIfInverted(int pos, int highIdx) {
            int high = getElement(pos, highIdx);
            int low = getElement(pos, highIdx - 1);
            if (high < low)
                return (pos & ~(0xFF << 4 * (highIdx - 1))) | (low << 4 * highIdx) | (high << 4 * (highIdx - 1));
            return pos;
        }

    }

    private static final Pattern RTG_PATTERN = Pattern.compile("(\\w+) generator");
    private static final Pattern CHIP_PATTERN = Pattern.compile("(\\w+)-compatible microchip");

    private record Parsed(Facility facility, int size) {}

    private Parsed parseInput() {
        Map<String, Integer> bits = new HashMap<>();
        for (String line : input) {
            Matcher rtg = RTG_PATTERN.matcher(line);
            while (rtg.find())
                bits.putIfAbsent(rtg.group(1), bits.size());
            Matcher chip = CHIP_PATTERN.matcher(line);
            while (chip.find())
                bits.putIfAbsent(chip.group(1), bits.size());
        }

        int positions = 0;
        for (int floor = 0; floor < input.size(); ++floor) {
            String line = input.get(floor);
            Matcher rtg = RTG_PATTERN.matcher(line);
            while (rtg.find())
                positions += floor << 4 * bits.get(rtg.group(1)) + 2;
            Matcher chip = CHIP_PATTERN.matcher(line);
            while (chip.find())
                positions += floor << 4 * bits.get(chip.group(1));
        }
        return new Parsed(new Facility(positions, 0, 0), bits.size());
    }

    public int a2() {
        Parsed initial = parseInput();
        return new Setup(initial.facility(), initial.size() + 2).moveAllUp();
    }
}
