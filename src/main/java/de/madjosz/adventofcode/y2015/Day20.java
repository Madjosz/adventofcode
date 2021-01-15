package de.madjosz.adventofcode.y2015;

import java.util.NoSuchElementException;


public class Day20 {

    private final int presents;

    public Day20(String string) {
        this.presents = Integer.parseInt(string);
    }

    public int a1() {
        int limit = presents / 10;
        int[] arr = new int[limit];
        for (int i = 1; i <= limit; ++i)
            for (int j = i - 1; j < limit; j += i)
                arr[j] += i * 10;
        for (int i = 0; i < limit; ++i)
            if (arr[i] >= presents) return i + 1;
        throw new NoSuchElementException();
    }

    public int a2() {
        int limit = presents / 11;
        int[] arr = new int[limit];
        for (int i = 1; i <= limit; ++i)
            for (int j = i - 1; j < 50 * i && j < limit; j += i)
                arr[j] += i * 11;
        for (int i = 0; i < limit; ++i)
            if (arr[i] >= presents) return i + 1;
        throw new NoSuchElementException();
    }
}
