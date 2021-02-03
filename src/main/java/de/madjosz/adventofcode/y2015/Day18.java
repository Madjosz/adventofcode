package de.madjosz.adventofcode.y2015;

import static java.util.stream.Collectors.toCollection;

import de.madjosz.adventofcode.data.Coord;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;


public class Day18 {

    private final int width;
    private final int height;
    private final Set<Coord> initial;

    public Day18(List<String> input) {
        this.width = input.get(0).length();
        this.height = input.size();
        this.initial = IntStream.range(0, input.size()).mapToObj(y -> {
            String line = input.get(y);
            return IntStream.range(0, line.length()).filter(x -> line.charAt(x) == '#').mapToObj(x -> new Coord(x, y));
        }).flatMap(Function.identity()).collect(toCollection(LinkedHashSet::new));
    }

    public int a1() {
        return a1(100);
    }

    int a1(int stop) {
        return new LimitedConway(width, height, initial).gen(stop).size();
    }

    private static class LimitedConway {

        protected final int width;
        protected final int height;
        private Set<Coord> alive;
        private final List<int[]> directions;

        public LimitedConway(int width, int height, Set<Coord> alive) {
            this.width = width;
            this.height = height;
            this.alive = initialNextGen();
            this.alive.addAll(alive);
            this.directions = makeDirections();
        }

        private static List<int[]> makeDirections() {
            List<int[]> dir = new ArrayList<>(8);
            for (int x = -1; x <= 1; ++x)
                for (int y = -1; y <= 1; ++y)
                    if (x != 0 || y != 0) dir.add(new int[] { x, y });
            return dir;
        }

        public Set<Coord> gen(int gen) {
            for (int i = 0; i < gen; ++i)
                nextGen();
            return alive;
        }

        private void nextGen() {
            Set<Coord> nextGen = initialNextGen();
            Map<Coord, Integer> deadNeighbors = new LinkedHashMap<>();
            for (Coord cell : alive) {
                int aliveNeigbors = 0;
                for (Coord neighbor : getNeighbors(cell)) {
                    if (alive.contains(neighbor)) ++aliveNeigbors;
                    else deadNeighbors.merge(neighbor, 1, Integer::sum);
                }
                if (aliveNeigbors == 2 || aliveNeigbors == 3) nextGen.add(cell);
            }
            deadNeighbors.entrySet().stream().filter(e -> e.getValue() == 3).map(Entry::getKey).forEach(nextGen::add);
            this.alive = nextGen;
        }

        private Iterable<Coord> getNeighbors(Coord cell) {
            return directions.stream().map(cell::add).filter(this::inGrid)::iterator;
        }

        private boolean inGrid(Coord c) {
            return c.x() >= 0 && c.x() < width && c.y() >= 0 && c.y() < height;
        }

        protected Set<Coord> initialNextGen() {
            return new LinkedHashSet<>();
        }

    }

    public int a2() {
        return a2(100);
    }

    public int a2(int stop) {
        return new LimitedConwayWithCornersOn(width, height, initial).gen(stop).size();
    }

    private static class LimitedConwayWithCornersOn extends LimitedConway {

        public LimitedConwayWithCornersOn(int width, int height, Set<Coord> alive) {
            super(width, height, alive);
        }

        @Override
        protected Set<Coord> initialNextGen() {
            Set<Coord> nextGen = super.initialNextGen();
            nextGen.addAll(List.of(new Coord(0, 0), new Coord(width - 1, 0), new Coord(width - 1, height - 1),
                    new Coord(0, height - 1)));
            return nextGen;
        }
    }
}
