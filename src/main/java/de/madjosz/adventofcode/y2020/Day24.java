package de.madjosz.adventofcode.y2020;

import static java.util.stream.Collectors.toList;

import de.madjosz.adventofcode.data.Coord;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class Day24 {

    enum D {

        E, SE, SW, W, NW, NE;

        Coord move(Coord c) {
            switch (this) {
            case E:
                return new Coord(c.x(), c.y() + 1);
            case W:
                return new Coord(c.x(), c.y() - 1);
            case SE:
                return new Coord(c.x() - 1, c.y() + 1);
            case SW:
                return new Coord(c.x() - 1, c.y());
            case NE:
                return new Coord(c.x() + 1, c.y());
            case NW:
                return new Coord(c.x() + 1, c.y() - 1);
            default:
                throw new IllegalStateException();
            }
        }
    }

    private final List<List<D>> moves;

    public Day24(List<String> input) {
        this.moves = input.stream().map(Day24::parseMoves).collect(toList());
    }

    private static List<D> parseMoves(String line) {
        List<D> moves = new ArrayList<>();
        line = line.toUpperCase();
        int pos = 0;
        while (pos < line.length()) {
            char c = line.charAt(pos);
            if (c == 'N' || c == 'S') moves.add(D.valueOf(line.substring(pos, pos += 2)));
            else moves.add(D.valueOf(line.substring(pos, ++pos)));
        }
        return moves;
    }

    public int a1() {
        Set<Coord> blackTiles = initialTiles();
        return blackTiles.size();
    }

    private Set<Coord> initialTiles() {
        Set<Coord> blackTiles = new LinkedHashSet<>();
        for (List<D> move : moves)
            toggleTile(blackTiles, move.stream().reduce(new Coord(0, 0), (c, d) -> d.move(c), (x, y) -> null));
        return blackTiles;
    }

    private static void toggleTile(Set<Coord> blackTiles, Coord tile) {
        if (!blackTiles.add(tile)) blackTiles.remove(tile);
    }

    public int a2() {
        Set<Coord> blackTiles = initialTiles();
        for (int gen = 0; gen < 100; ++gen) {
            Set<Coord> newGen = new LinkedHashSet<>();
            Map<Coord, Integer> whiteNeighbors = new LinkedHashMap<>();
            for (Coord black : blackTiles) {
                int blackNeighbors = 0;
                for (Coord neighbor : Arrays.stream(D.values()).map(d -> d.move(black)).collect(toList())) {
                    if (blackTiles.contains(neighbor)) ++blackNeighbors;
                    else whiteNeighbors.merge(neighbor, 1, Integer::sum);
                }
                if (blackNeighbors == 1 || blackNeighbors == 2) newGen.add(black);
            }
            whiteNeighbors.entrySet().stream().filter(e -> e.getValue() == 2).map(Entry::getKey).forEach(newGen::add);
            blackTiles = newGen;
        }
        return blackTiles.size();
    }

}
