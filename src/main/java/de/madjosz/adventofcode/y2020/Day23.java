package de.madjosz.adventofcode.y2020;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Queue;


public class Day23 {

    private final int[] cups;

    public Day23(List<String> input) {
        this.cups = input.stream().flatMapToInt(String::chars).map(i -> i - '0').toArray();
    }

    /* NAIVE QUEUE */

    public String _a1() {
        Deque<Integer> queue = new ArrayDeque<>(Arrays.stream(cups).boxed().collect(toList()));
        play(queue, 100);
        return toString(queue);
    }

    private static void play(Deque<Integer> queue, int rounds) {
        for (int i = 0; i < rounds; ++i) {
            int current = queue.poll();
            List<Integer> pickup = pickUp3(queue);
            int search = current;
            do {
                if (--search == 0) search = 9;
            } while (pickup.contains(search));
            placePickup(search, queue, pickup);
            queue.offer(current);
        }
    }

    private static void placePickup(int search, Deque<Integer> queue, List<Integer> pickup) {
        List<Integer> taken = new ArrayList<>();
        int head;
        do {
            head = queue.poll();
            taken.add(head);
        } while (head != search);
        for (int i = pickup.size() - 1; i >= 0; --i)
            queue.offerFirst(pickup.get(i));
        for (int i = taken.size() - 1; i >= 0; --i)
            queue.offerFirst(taken.get(i));
    }

    private static List<Integer> pickUp3(Deque<Integer> queue) {
        List<Integer> pickup = new ArrayList<>(3);
        pickup.add(queue.poll());
        pickup.add(queue.poll());
        pickup.add(queue.poll());
        return pickup;
    }

    private static String toString(Collection<Integer> list) {
        Queue<Integer> q = new ArrayDeque<>(list);
        int i;
        while ((i = q.poll()) != 1)
            q.offer(i);
        return q.stream().map(j -> j.toString()).collect(joining());
    }

    /* SYSTEM ARRAY COPY */

    private static final int MILLION = 1_000_000;

    public long _a2() {
        return _a2(MILLION, 10 * MILLION);
    }

    private long _a2(int cupNumber, int turns) {
        int[] game = createStartArray(cupNumber);
        int current = 0;
        for (int i = 0; i < turns; ++i) {
            if (i % 10000 == 0) System.out.println(i);
            if (current >= game.length - 3) {
                moveCurrentTo0(current, game);
                current = 0;
            }
            int[] pickup = new int[3];
            System.arraycopy(game, current + 1, pickup, 0, 3);
            int search = game[current];
            do {
                if (--search == 0) search = game.length;
            } while (contains(search, pickup));
            int idx = indexOf(search, game);
            if (idx > current) {
                // 0 0 0 0 X - - - 1 1 1 1 Y 2 2 2 2
                // 0 0 0 0 X 1 1 1 1 Y - - - 2 2 2 2
                System.arraycopy(game, current + 4, game, current + 1, idx - current - 3);
                System.arraycopy(pickup, 0, game, idx - 2, 3);
                ++current;
            } else {
                // 0 0 0 0 Y 1 1 1 1 X - - - 2 2 2 2
                // 0 0 0 0 Y - - - 1 1 1 1 X 2 2 2 2
                System.arraycopy(game, idx + 1, game, idx + 4, current - idx);
                System.arraycopy(pickup, 0, game, idx + 1, 3);
                current += 4;
            }
        }
        return getResult(game);
    }

    private static void moveCurrentTo0(int current, int[] game) {
        int[] tail = Arrays.copyOfRange(game, current, game.length);
        System.arraycopy(game, 0, game, game.length - current, current);
        System.arraycopy(tail, 0, game, 0, tail.length);
    }

    private int[] createStartArray(int count) {
        int[] game = new int[count];
        System.arraycopy(cups, 0, game, 0, cups.length);
        for (int i = cups.length; i < count; ++i)
            game[i] = i + 1;
        return game;
    }

    private static boolean contains(int needle, int[] haystack) {
        for (int hay : haystack)
            if (needle == hay) return true;
        return false;
    }

    private static int indexOf(int needle, int[] haystack) {
        for (int i = 0; i < haystack.length; ++i)
            if (needle == haystack[i]) return i;
        return -1;
    }

    private static long getResult(int[] game) {
        for (int i = 0; i < game.length; ++i)
            if (game[i] == 1) return (long) game[(i + 1) % game.length] * game[(i + 2) % game.length];
        throw new IllegalStateException();
    }

    /* SUCCESSOR ARRAY */

    public String a1() {
        return a1(100);
    }

    public String a1(int moves) {
        int[] game = play(cups.length, moves);
        StringBuilder s = new StringBuilder();
        int i = game[0];
        do {
            s.append(i + 1);
            i = game[i];
        } while (i != 0);
        return s.toString();
    }

    public long a2() {
        int[] game = play(MILLION, 10 * MILLION);
        return (long) (game[0] + 1) * (game[game[0]] + 1);
    }

    private int[] play(int size, int runs) {
        int[] game = createGame(size);
        int idx = cups[0] - 1;
        for (int i = 0; i < runs; ++i) {
            int b1 = game[idx];
            int b2 = game[b1];
            int b3 = game[b2];
            int next = idx;
            do {
                if (--next < 0) next = game.length - 1;
            } while (next == b1 || next == b2 || next == b3);
            int temp = game[next];
            game[idx] = game[b3];
            game[next] = b1;
            game[b3] = temp;
            idx = game[idx];
        }
        return game;
    }

    private int[] createGame(int size) {
        int[] game = new int[size];
        for (int i = 0; i < cups.length - 1; ++i)
            game[cups[i] - 1] = cups[i + 1] - 1;
        if (size == cups.length) game[cups[cups.length - 1] - 1] = cups[0] - 1;
        else {
            game[cups[cups.length - 1] - 1] = cups.length;
            for (int i = cups.length; i < game.length - 1; ++i)
                game[i] = i + 1;
            game[game.length - 1] = cups[0] - 1;
        }
        return game;
    }
}
