package de.madjosz.adventofcode.y2020;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.IntStream;


public class Day17 {

    private final boolean[][] initial;

    public Day17(List<String> input) {
        this.initial = input.stream().map(Day17::parseLine).toArray(boolean[][]::new);
    }

    private static boolean[] parseLine(String line) {
        boolean[] arr = new boolean[line.length()];
        for (int i = 0; i < arr.length; ++i)
            arr[i] = line.charAt(i) == '#';
        return arr;
    }

    public int _a1() {
        Conway3D conway = new Conway3D(initial);
        boolean[][][] gen6 = conway.gen(6);
        return count(gen6);
    }

    public int a1() {
        ConwayND conway = new ConwayND(3, initial);
        Set<Coor> gen6 = conway.gen(6);
        return gen6.size();
    }

    private static int count(boolean[][][] alive) {
        return Arrays.stream(alive)
                .mapToInt(g -> Arrays.stream(g)
                        .mapToInt(l -> IntStream.range(0, l.length).map(i -> l[i] ? 1 : 0).sum())
                        .sum())
                .sum();
    }

    private static class Conway3D {

        private boolean[][][] alive;
        private int rows;
        private int cols;
        private int heig;
        private static final List<int[]> dirs = createDirs();

        private static List<int[]> createDirs() {
            List<int[]> dirs = new ArrayList<>(26);
            for (int i = -1; i <= 1; ++i)
                for (int j = -1; j <= 1; ++j)
                    for (int k = -1; k <= 1; ++k)
                        if (i != 0 || j != 0 || k != 0) dirs.add(new int[] { i, j, k });
            return dirs;
        }

        public Conway3D(boolean[][] initial) {
            this.alive = new boolean[][][] { initial };
            this.rows = initial.length;
            this.cols = initial[0].length;
            this.heig = 1;
        }

        public boolean[][][] gen(int gen) {
            while (gen-- > 0)
                nextGen();
            return alive;
        }

        private void nextGen() {
            boolean[][][] nextGen = new boolean[heig + 2][rows + 2][cols + 2];
            for (int h = 0; h < nextGen.length; ++h) {
                boolean[][] grid = nextGen[h];
                for (int r = 0; r < grid.length; ++r) {
                    boolean[] line = grid[r];
                    for (int c = 0; c < line.length; ++c) {
                        int aliveNeighbors = aliveNeighbors(h - 1, r - 1, c - 1);
                        if (aliveNeighbors == 3 || (aliveNeighbors == 2 && isAlive(h - 1, r - 1, c - 1)))
                            line[c] = true;
                    }
                }
            }
            update(nextGen);
        }

        private int aliveNeighbors(int h, int r, int c) {
            return (int) dirs.stream().filter(d -> isNeighborAlive(h, r, c, d)).count();
        }

        private boolean isNeighborAlive(int he, int ro, int co, int[] di) {
            int h = he + di[0];
            int r = ro + di[1];
            int c = co + di[2];
            return isAlive(h, r, c);
        }

        private boolean isAlive(int h, int r, int c) {
            return checkH(h) && checkR(r) && checkC(c) && alive[h][r][c];
        }

        private boolean checkH(int h) {
            return 0 <= h && h < heig;
        }

        private boolean checkR(int r) {
            return 0 <= r && r < rows;
        }

        private boolean checkC(int c) {
            return 0 <= c && c < cols;
        }

        private void update(boolean[][][] nextGen) {
            alive = nextGen;
            rows += 2;
            cols += 2;
            heig += 2;
        }
    }

    public int a2() {
        ConwayND conway = new ConwayND(4, initial);
        Set<Coor> gen6 = conway.gen(6);
        return gen6.size();
    }

    private static class ConwayND {

        private Set<Coor> alive;
        private final List<int[]> dirs;

        public ConwayND(int n, boolean[][] initial) {
            this.alive = createInitial(n, initial);
            this.dirs = createDirs(n);
        }

        private static Set<Coor> createInitial(int n, boolean[][] initial) {
            Set<Coor> alive = new LinkedHashSet<>();
            for (int i = 0; i < initial.length; ++i)
                for (int j = 0; j < initial[i].length; ++j)
                    if (initial[i][j]) alive.add(new Coor(n, i, j));
            return alive;
        }

        private static List<int[]> createDirs(int n) {
            int[] dir = new int[n];
            List<int[]> dirs = new ArrayList<>();
            createDirs(0, dir, dirs);
            dirs.remove(dirs.size() / 2);
            return dirs;
        }

        private static void createDirs(int i, int[] dir, List<int[]> dirs) {
            if (i == dir.length) dirs.add(dir.clone());
            else for (int j = -1; j <= 1; ++j) {
                dir[i] = j;
                createDirs(i + 1, dir, dirs);
            }
        }

        public Set<Coor> gen(int gen) {
            while (gen-- > 0)
                nextGen();
            return alive;
        }

        private void nextGen() {
            Set<Coor> nextGen = new LinkedHashSet<>();
            Map<Coor, Integer> deadNeighbors = new LinkedHashMap<>();
            for (Coor coor : alive) {
                int aliveNeighbors = 0;
                for (Coor neighbor : getNeighbors(coor)) {
                    if (alive.contains(neighbor)) ++aliveNeighbors;
                    else deadNeighbors.merge(neighbor, 1, Integer::sum);
                }
                if (aliveNeighbors == 2 || aliveNeighbors == 3) nextGen.add(coor);
            }
            deadNeighbors.entrySet().stream().filter(e -> e.getValue() == 3).map(Entry::getKey).forEach(nextGen::add);
            this.alive = nextGen;
        }

        private List<Coor> getNeighbors(Coor coor) {
            return dirs.stream().map(coor::add).collect(toList());
        }
    }

    private static class Coor {

        private final int[] c;
        private final int hash;

        public Coor(int[] c) {
            this.c = c;
            this.hash = Arrays.hashCode(c);
        }

        public Coor(int n, int x, int y) {
            this(c(n, x, y));
        }

        private static int[] c(int n, int x, int y) {
            int[] c = new int[n];
            c[0] = x;
            c[1] = y;
            return c;
        }

        public Coor add(int[] d) {
            int[] c2 = c.clone();
            for (int i = 0; i < c2.length; ++i)
                c2[i] += d[i];
            return new Coor(c2);
        }

        @Override
        public int hashCode() {
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Coor other && Arrays.equals(c, other.c);
        }
    }
}
