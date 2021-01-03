package de.madjosz.adventofcode.y2020;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class Day22 {

    private final List<Integer> cardsP1;
    private final List<Integer> cardsP2;

    public Day22(List<String> input) {
        int lastLineP1 = input.indexOf("");
        this.cardsP1 = parseCards(input.subList(1, lastLineP1));
        this.cardsP2 = parseCards(input.subList(lastLineP1 + 2, input.size()));
    }

    private List<Integer> parseCards(List<String> lines) {
        return lines.stream().map(Integer::valueOf).collect(toList());
    }

    public int a1() {
        Queue<Integer> p1 = new ArrayDeque<>(cardsP1);
        Queue<Integer> p2 = new ArrayDeque<>(cardsP2);

        while (!p1.isEmpty() && !p2.isEmpty()) {
            int c1 = p1.poll();
            int c2 = p2.poll();
            if (c1 > c2) {
                p1.offer(c1);
                p1.offer(c2);
            } else {
                p2.offer(c2);
                p2.offer(c1);
            }
        }
        Queue<Integer> winner = p1.isEmpty() ? p2 : p1;
        int sum = 0;
        while (!winner.isEmpty())
            sum += winner.size() * winner.poll();
        return sum;
    }

    public Integer a2() {
        Game game = new Game(cardsP1, cardsP2);
        return Math.abs(game.getWinnerScore());
    }

    private static class Game {

        private final Queue<Integer> p1;
        private final Queue<Integer> p2;

        private final Set<Decks> rounds = new HashSet<>();

        public Game(List<Integer> p1, List<Integer> p2) {
            this.p1 = new ArrayDeque<>(p1);
            this.p2 = new ArrayDeque<>(p2);
        }

        public int getWinnerScore() {
            while (!p1.isEmpty() && !p2.isEmpty()) {
                Integer[] arrayP1 = p1.toArray(new Integer[0]);
                Integer[] arrayP2 = p2.toArray(new Integer[0]);
                if (!rounds.add(new Decks(arrayP1, arrayP2))) return getScore(p1);
                int c1 = p1.poll();
                int c2 = p2.poll();
                boolean p1Winner;
                if (c1 <= p1.size() && c2 <= p2.size()) {
                    List<Integer> newP1 = asList(arrayP1).subList(1, c1 + 1);
                    List<Integer> newP2 = asList(arrayP2).subList(1, c2 + 1);
                    Game subGame = new Game(newP1, newP2);
                    p1Winner = subGame.getWinnerScore() > 0;
                } else p1Winner = c1 > c2;
                if (p1Winner) {
                    p1.offer(c1);
                    p1.offer(c2);
                } else {
                    p2.offer(c2);
                    p2.offer(c1);
                }
            }
            return p1.isEmpty() ? -getScore(p2) : getScore(p1);
        }

        private int getScore(Queue<Integer> winner) {
            int sum = 0;
            while (!winner.isEmpty())
                sum += winner.size() * winner.poll();
            return sum;
        }

        private static class Decks {

            private final Integer[] p1;
            private final Integer[] p2;
            private final int hash;

            public Decks(Integer[] p1, Integer[] p2) {
                this.p1 = p1;
                this.p2 = p2;
                this.hash = 37 * Arrays.hashCode(p1) + Arrays.hashCode(p2);
            }

            @Override
            public int hashCode() {
                return hash;
            }

            @Override
            public boolean equals(Object obj) {
                return obj instanceof Decks other && Arrays.equals(this.p1, other.p1)
                        && Arrays.equals(this.p2, other.p2);
            }
        }
    }

}
