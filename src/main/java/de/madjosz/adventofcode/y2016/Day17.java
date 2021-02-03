package de.madjosz.adventofcode.y2016;

import de.madjosz.adventofcode.data.Coord;
import de.madjosz.adventofcode.data.MD5;
import java.security.MessageDigest;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;


public class Day17 {

    private final String passcode;
    private static final MessageDigest md5 = MD5.get();

    private record Path(Coord room, String path) {}

    private record Doors(boolean up, boolean down, boolean left, boolean right) {}

    public Day17(String input) {
        this.passcode = input;
    }

    public String a1() {
        Queue<Path> paths = new ArrayDeque<>();
        paths.offer(new Path(new Coord(0, 0), ""));
        while (!paths.isEmpty()) {
            Path current = paths.poll();
            Coord room = current.room();
            String path = current.path();
            if (room.x() == 3 && room.y() == 3) return path;
            Doors doors = getOpenDoors(path);
            if (room.y() > 0 && doors.up()) paths.add(new Path(new Coord(room.x(), room.y() - 1), path + "U"));
            if (room.y() < 3 && doors.down()) paths.add(new Path(new Coord(room.x(), room.y() + 1), path + "D"));
            if (room.x() > 0 && doors.left()) paths.add(new Path(new Coord(room.x() - 1, room.y()), path + "L"));
            if (room.x() < 3 && doors.right()) paths.add(new Path(new Coord(room.x() + 1, room.y()), path + "R"));
        }
        throw new IllegalArgumentException();
    }

    private Doors getOpenDoors(String path) {
        byte[] hash = md5.digest((passcode + path).getBytes());
        return new Doors((Byte.toUnsignedInt(hash[0]) >> 4) > 10, (hash[0] & 0xF) > 10,
                (Byte.toUnsignedInt(hash[1]) >> 4) > 10, (hash[1] & 0xF) > 10);
    }

    public int a2() {
        Queue<Path> paths = new ArrayDeque<>();
        AtomicInteger longestPath = new AtomicInteger(-1);
        paths.offer(new Path(new Coord(0, 0), ""));
        while (!paths.isEmpty()) {
            Path current = paths.poll();
            Coord room = current.room();
            String path = current.path();
            if (room.x() == 3 && room.y() == 3) longestPath.accumulateAndGet(path.length(), Math::max);
            else {
                Doors doors = getOpenDoors(path);
                if (room.y() > 0 && doors.up()) paths.add(new Path(new Coord(room.x(), room.y() - 1), path + "U"));
                if (room.y() < 3 && doors.down()) paths.add(new Path(new Coord(room.x(), room.y() + 1), path + "D"));
                if (room.x() > 0 && doors.left()) paths.add(new Path(new Coord(room.x() - 1, room.y()), path + "L"));
                if (room.x() < 3 && doors.right()) paths.add(new Path(new Coord(room.x() + 1, room.y()), path + "R"));
            }
        }
        if (longestPath.get() == -1) throw new IllegalArgumentException();
        return longestPath.get();
    }
}
