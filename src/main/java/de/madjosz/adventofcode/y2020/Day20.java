package de.madjosz.adventofcode.y2020;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Day20 {

    private final List<String> lines;

    public Day20(List<String> input) {
        this.lines = input;
    }

    public long a1() {
        Map<Integer, List<List<Integer>>> tiles = parseTileBorders();
        return findCorners(tiles).mapToLong(i -> i).reduce(1L, (a, b) -> a * b);
    }

    private static IntStream findCorners(Map<Integer, List<List<Integer>>> tiles) {
        return tiles.entrySet().stream().filter(tile -> {
            List<Integer> sides = tile.getValue().get(0);
            return tiles.entrySet()
                    .stream()
                    .filter(e -> !e.getKey().equals(tile.getKey()))
                    .filter(e -> e.getValue().stream().flatMap(Collection::stream).anyMatch(sides::contains))
                    .count() == 2;
        }).mapToInt(Entry::getKey);
    }

    private Map<Integer, List<List<Integer>>> parseTileBorders() {
        Map<Integer, List<List<Integer>>> tiles = new LinkedHashMap<>();
        for (int i = 0; i < lines.size(); i += 12) {
            int tileId = Integer.parseInt(lines.get(i).substring(5, 9));
            int[] top = parseLine(i + 1);
            int[] bottom = parseLine(i + 10);
            int[] sides = parseCols(i + 1);
            tiles.put(tileId, List.of(List.of(top[0], sides[0], bottom[1], sides[3]),
                    List.of(top[1], sides[2], bottom[0], sides[1])));
        }
        return tiles;
    }

    private int[] parseLine(int idx) {
        String line = lines.get(idx).replace('#', '1').replace('.', '0');
        return new int[] { Integer.parseInt(line, 2),
                Integer.parseInt(new StringBuilder(line).reverse().toString(), 2) };
    }

    private int[] parseCols(int idx) {
        StringBuilder c1 = new StringBuilder();
        StringBuilder c2 = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            String line = lines.get(idx + i);
            c1.append(line.charAt(0) == '#' ? '1' : '0');
            c2.append(line.charAt(9) == '#' ? '1' : '0');
        }
        return new int[] { parse(c1.toString()), parse(c1.reverse().toString()), parse(c2.toString()),
                parse(c2.reverse().toString()) };
    }

    private static int parse(String s) {
        return Integer.parseInt(s, 2);
    }

    public int a2() {
        Map<Integer, Tile> tiles = parseTiles();
        List<String> photo = new TileAligner(tiles).alignAll();
        return new SeamonsterTracker(photo).getRoughSeaCount();
    }

    private Map<Integer, Tile> parseTiles() {
        return IntStream.iterate(0, i -> i < lines.size(), i -> i + 12)
                .mapToObj(this::parseTile)
                .collect(toMap(Tile::getId, Function.identity()));
    }

    private static class TileAligner {

        private final Tile[][] aligned;
        private final Map<Integer, Tile> tiles;

        public TileAligner(Map<Integer, Tile> tiles) {
            this.tiles = tiles;
            int size = (int) Math.sqrt(tiles.size());
            this.aligned = new Tile[size][size];
        }

        public List<String> alignAll() {
            alignCorner();
            alignRow(0);
            alignColumns();
            return collapse();
        }

        private List<String> collapse() {
            List<String> collapsed = new ArrayList<>();
            for (int row = 0; row < aligned.length; row++) {
                Tile[] line = aligned[row];
                for (int r = 0; r < line[0].image.size(); ++r) {
                    StringBuilder str = new StringBuilder();
                    for (int col = 0; col < line.length; ++col)
                        str.append(line[col].image.get(r));
                    collapsed.add(str.toString());
                }
            }
            return collapsed;
        }

        private void alignRow(int row) {
            for (int col = 1; col < aligned[row].length; ++col)
                alignTile(row, col, 3);
        }

        private void alignTile(int row, int col, int borderidx) {
            Tile fixed = aligned[row + (borderidx % 2 == 0 ? borderidx - 1 : 0)][col
                    + (borderidx % 2 == 1 ? 2 - borderidx : 0)];
            Integer border = fixed.getBorders().get((borderidx + 2) % 4);
            Tile matchingTile = orientTile(fixed, findMatchingTile(border), borderidx);
            tiles.remove(matchingTile.getId());
            aligned[row][col] = matchingTile;
        }

        private void alignColumns() {
            for (int row = 1; row < aligned.length; ++row) {
                alignTile(row, 0, 0);
                alignRow(row);
            }

        }

        private Tile orientTile(Tile fixedTile, Tile matchingTile, int matchposition) {
            int[] orientation = fixedTile.orientation(matchingTile);
            if (orientation[1] < 0) {
                matchingTile = matchingTile.flipD();
                orientation = fixedTile.orientation(matchingTile);
            }
            while (orientation[1] != matchposition) {
                matchingTile = matchingTile.rotateR();
                orientation = fixedTile.orientation(matchingTile);
            }
            return matchingTile;
        }

        private Tile findMatchingTile(Integer border) {
            return tiles.entrySet()
                    .stream()
                    .filter(e -> e.getValue().getPossibleBorders().anyMatch(border::equals))
                    .map(Entry::getValue)
                    .findAny()
                    .orElseThrow();
        }

        private void alignCorner() {
            int corner = findCorner();
            Tile cornerTile = tiles.remove(corner);
            List<Integer> sides = cornerTile.getBorders();
            List<Tile> matchingCorner = tiles.entrySet()
                    .stream()
                    .filter(e -> e.getValue().getPossibleBorders().anyMatch(sides::contains))
                    .map(Entry::getValue)
                    .collect(toList());

            int[] c1 = cornerTile.orientation(matchingCorner.get(0));
            int[] c2 = cornerTile.orientation(matchingCorner.get(1));
            while (!Set.of(c1[0], c2[0]).containsAll(Set.of(1, 2))) {
                cornerTile = cornerTile.rotateR();
                c1 = cornerTile.orientation(matchingCorner.get(0));
                c2 = cornerTile.orientation(matchingCorner.get(1));
            }
            aligned[0][0] = cornerTile;
            tiles.remove(cornerTile.getId());
        }

        private int findCorner() {
            return tiles.entrySet()
                    .stream()
                    .filter(e -> tiles.entrySet()
                            .stream()
                            .filter(o -> !e.getKey().equals(o.getKey()))
                            .filter(o -> o.getValue()
                                    .getPossibleBorders()
                                    .anyMatch(e.getValue().getBorders()::contains))
                            .count() == 2)
                    .mapToInt(Entry::getKey)
                    .findAny()
                    .orElseThrow();
        }
    }

    private class Tile {

        private final int id;
        private final List<String> image;
        private final List<Integer> bordersR;
        private final List<Integer> bordersL;
        private final List<String> raw;

        private Tile(int id, List<String> image, int[] bordersR, int[] bordersL, List<String> raw) {
            this.id = id;
            this.image = image;
            this.bordersR = Arrays.stream(bordersR).boxed().collect(toList());
            this.bordersL = Arrays.stream(bordersL).boxed().collect(toList());
            this.raw = raw;
        }

        public int getId() {
            return id;
        }

        public List<Integer> getBorders() {
            return bordersR;
        }

        public Stream<Integer> getPossibleBorders() {
            return Stream.concat(bordersR.stream(), bordersL.stream());
        }

        public Tile rotateR() {
            Integer[] r = bordersR.toArray(new Integer[0]);
            Integer[] l = bordersL.toArray(new Integer[0]);
            return new Tile(id, Day20.rotateR(image), new int[] { r[3], r[0], r[1], r[2] },
                    new int[] { l[3], l[0], l[1], l[2] }, Day20.rotateR(raw));
        }

        public Tile flipD() {
            Integer[] r = bordersR.toArray(new Integer[0]);
            Integer[] l = bordersL.toArray(new Integer[0]);
            return new Tile(id, Day20.flipD(image), new int[] { l[3], l[2], l[1], l[0] },
                    new int[] { r[3], r[2], r[1], r[0] }, Day20.flipD(raw));
        }

        public int[] orientation(Tile other) {
            List<Integer> b = other.getBorders();
            for (int i = 0; i < 4; ++i)
                for (int j = 0; j < 4; ++j) {
                    if (bordersL.get(i).equals(b.get(j))) return new int[] { i, j };
                    if (bordersR.get(i).equals(b.get(j))) return new int[] { i, ~j };
                }
            throw new IllegalArgumentException();
        }
    }

    public Tile parseTile(int pos) {
        int tileId = Integer.parseInt(lines.get(pos).substring(5, 9));
        int[] top = parseLine(pos + 1);
        int[] bottom = parseLine(pos + 10);
        int[] sides = parseCols(pos + 1);
        List<String> image = IntStream.range(pos + 2, pos + 10)
                .mapToObj(lines::get)
                .map(s -> s.substring(1, 9))
                .collect(toList());
        return new Tile(tileId, image, new int[] { top[0], sides[2], bottom[1], sides[1] },
                new int[] { top[1], sides[3], bottom[0], sides[0] }, lines.subList(pos + 1, pos + 11));
    }

    private static class SeamonsterTracker {

        @SuppressWarnings("unused")
        private static final String SEAMONSTER1 = "..................#.";
        private static final String SEAMONSTER2 = "#....##....##....###";
        private static final String SEAMONSTER3 = ".#..#..#..#..#..#...";
        private static final Pattern p2 = Pattern.compile(SEAMONSTER2);
        private static final Pattern p3 = Pattern.compile(SEAMONSTER3);

        private final List<String> photo;
        private int seamonster = 0;

        public SeamonsterTracker(List<String> photo) {
            this.photo = photo;
        }

        public int getRoughSeaCount() {
            if (seamonster == 0) countSeaMonsters();
            return (int) photo.stream().flatMapToInt(String::chars).filter(c -> c == '#').count() - seamonster * 15;
        }

        private void countSeaMonsters() {
            List<String> rotatedPhoto = photo;
            for (int i = 0; i < 8 && seamonster == 0; ++i) {
                if (i == 4) rotatedPhoto = flipD(rotatedPhoto);
                else if (i % 4 != 0) rotatedPhoto = rotateR(rotatedPhoto);
                countSeaMonsters(rotatedPhoto);
            }
        }

        private void countSeaMonsters(List<String> rotatedPhoto) {
            for (int row = 1; row < rotatedPhoto.size() - 1; ++row) {
                Matcher m = p2.matcher(rotatedPhoto.get(row));
                int start = 0;
                while (m.find(start)) {
                    Matcher m2 = p3.matcher(rotatedPhoto.get(row + 1));
                    if (m2.find(m.start()) && m2.start() == m.start()
                            && rotatedPhoto.get(row - 1).charAt(m.start() + 18) == '#')
                        ++seamonster;
                    start = m.start() + 1;
                }
            }
        }
    }

    private static List<String> flipD(List<String> img) {
        int size = img.size();
        List<String> flipped = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            StringBuilder line = new StringBuilder(size);
            for (int j = 0; j < size; ++j)
                line.append(img.get(j).charAt(i));
            flipped.add(line.toString());
        }
        return flipped;
    }

    private static List<String> rotateR(List<String> img) {
        int size = img.size();
        List<String> rotated = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            StringBuilder line = new StringBuilder(size);
            for (int j = size - 1; j >= 0; --j)
                line.append(img.get(j).charAt(i));
            rotated.add(line.toString());
        }
        return rotated;
    }
}
