package de.madjosz.adventofcode.y2016;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class Day04 {

    private final List<Room> rooms;

    private static final Pattern ROOM = Pattern.compile("([a-z\\-]+)-(\\d+)\\[([a-z\\-]+)\\]");

    private record Room(String name, int sectorid, String checksum) {

        private Room shiftName() {
            String shifted = name().chars()
                    .map(i -> i == '-' ? ' ' : (((i + sectorid - 'a') % 26) + 'a'))
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            return new Room(shifted, sectorid, checksum);
        }
    }

    public Day04(List<String> input) {
        this.rooms = input.stream()
                .map(ROOM::matcher)
                .filter(Matcher::find)
                .map(g -> new Room(g.group(1), Integer.parseInt(g.group(2)), g.group(3)))
                .collect(toList());
    }

    public int a1() {
        return validRooms().mapToInt(Room::sectorid).sum();
    }

    private Stream<Room> validRooms() {
        return rooms.stream().filter(this::isValidRoom);
    }

    private boolean isValidRoom(Room room) {
        String checksum = Arrays.stream(room.name().replace("-", "").split(""))
                .collect(groupingBy(identity(), counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.<Entry<String, Long>, Long> comparing(Map.Entry::getValue)
                        .reversed()
                        .thenComparing(Entry::getKey))
                .map(Entry::getKey)
                .limit(5)
                .collect(joining());
        return room.checksum().equals(checksum);
    }

    public int a2() {
        return a2("northpole object");
    }

    int a2(String neededRoom) {
        return validRooms().map(Room::shiftName)
                .filter(r -> r.name().startsWith(neededRoom))
                .map(Room::sectorid)
                .findAny()
                .orElseThrow();
    }

}
