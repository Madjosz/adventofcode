package de.madjosz.adventofcode.y2016;

import static de.madjosz.adventofcode.data.Coord.DIRECTIONS;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import de.madjosz.adventofcode.data.Coord;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;


public class Day22 {

    private final List<Node> nodes;

    private record Node(Coord coord, int used, int avail) {

        Node(int[] array) {
            this(new Coord(array[0], array[1]), array[2], array[3]);
        }
    }

    private static final Pattern DF = Pattern
            .compile("/dev/grid/node-x(\\d+)-y(\\d+)\\s+\\d+T\s+(\\d+)T\\s+(\\d+)T\\s+\\d+%");

    public Day22(List<String> input) {
        this.nodes = input.stream()
                .skip(2)
                .map(DF::matcher)
                .filter(Matcher::find)
                .map(m -> IntStream.rangeClosed(1, 4).mapToObj(m::group).mapToInt(Integer::parseInt).toArray())
                .map(Node::new)
                .collect(toList());
    }

    public int a1() {
        List<Node> sortedUsed = new ArrayList<>(nodes);
        Collections.sort(sortedUsed, comparingInt(Node::used));
        List<Node> sortedAvail = new ArrayList<>(nodes);
        Collections.sort(sortedAvail, comparingInt(Node::avail).reversed());

        int viablePairs = 0;
        int availIdx = nodes.size();
        for (Node node : sortedUsed) {
            int used = node.used();
            if (used == 0) continue;
            while (availIdx > 0 && used > sortedAvail.get(availIdx - 1).avail())
                --availIdx;
            viablePairs += availIdx;
            if (node.used() <= node.avail()) --viablePairs;
        }
        return viablePairs;
    }

    private record Path(Coord coord, int steps) {}

    public int a2() {
        Node empty = nodes.stream().filter(n -> n.used() == 0).findAny().orElseThrow();
        Set<Coord> full = nodes.stream().filter(n -> n.used() > empty.avail()).map(Node::coord).collect(toSet());
        int X = nodes.stream().mapToInt(n -> n.coord().x()).max().getAsInt();
        int Y = nodes.stream().mapToInt(n -> n.coord().y()).max().getAsInt();
        Coord goal = new Coord(X - 1, 0);

        Set<Coord> seen = new HashSet<>();
        Queue<Path> open = new ArrayDeque<>();
        seen.add(empty.coord());
        open.offer(new Path(empty.coord(), 0));
        while (!open.isEmpty()) {
            Path current = open.poll();
            Coord pos = current.coord();
            if (pos.equals(goal)) return addRemainingSteps(current.steps(), X);
            for (int[] dir : DIRECTIONS) {
                Coord nextPos = pos.add(dir);
                if (nextPos.x() <= X && nextPos.x() >= 0 && nextPos.y() <= Y && nextPos.y() >= 0 && seen.add(nextPos)
                        && !full.contains(nextPos))
                    open.offer(new Path(nextPos, current.steps() + 1));
            }
        }
        throw new IllegalStateException();
    }

    private int addRemainingSteps(int steps, int X) {
        // TODO this only works when there are no full nodes in the first two rows
        return steps + 5 * (X - 1) + 1;
    }
}
