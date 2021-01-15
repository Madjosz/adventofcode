package de.madjosz.adventofcode.y2015;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toCollection;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;


public class Day07 {

    private final List<String> input;

    public Day07(List<String> input) {
        this.input = input;
    }

    public int a1() {
        return wireThem().get("a");
    }

    private static final Pattern CIRCUT = Pattern
            .compile("(?:(?:(\\w+) )?(AND|OR|LSHIFT|RSHIFT|NOT) )?(\\w+|\\d+) -> (\\w+)");

    Map<String, Integer> wireThem() {
        return wireThem(null);
    }

    private Map<String, Integer> wireThem(Integer replacement) {
        List<String[]> circuts = parseCircuts();
        Map<String, Integer> wired = initialWiring(circuts);
        if (replacement != null) wired.put("b", replacement);
        wireTheRest(circuts, wired);
        return wired;
    }

    private List<String[]> parseCircuts() {
        return input.stream()
                .map(CIRCUT::matcher)
                .filter(Matcher::find)
                .map(m -> IntStream.rangeClosed(1, 4).mapToObj(m::group).toArray(String[]::new))
                .collect(toCollection(LinkedList::new));
    }

    private Map<String, Integer> initialWiring(List<String[]> circuts) {
        Map<String, Integer> wired = new LinkedHashMap<>();

        for (Iterator<String[]> iterator = circuts.iterator(); iterator.hasNext();) {
            String[] circut = iterator.next();
            if (circut[1] == null && isNumber(circut[2])) {
                wired.put(circut[3], Integer.valueOf(circut[2]));
                iterator.remove();
            }
        }
        return wired;
    }

    private void wireTheRest(List<String[]> circuts, Map<String, Integer> wired) {
        while (!circuts.isEmpty()) {
            for (Iterator<String[]> iterator = circuts.iterator(); iterator.hasNext();) {
                String[] circut = iterator.next();
                if (isForwardingWire(wired, circut)) {
                    wired.put(circut[3], wired.get(circut[2]));
                    iterator.remove();
                } else if (inputWiresAreSet(wired, circut)) {
                    wireCircut(wired, circut);
                    iterator.remove();
                }
            }
        }
    }

    private void wireCircut(Map<String, Integer> wired, String[] circut) {
        int wire1 = getValue(circut[0], wired);
        int wire2 = getValue(circut[2], wired);
        int value = switch (circut[1]) {
        case "AND" -> wire1 & wire2;
        case "OR" -> wire1 | wire2;
        case "LSHIFT" -> 0xFFFF & (wire1 << wire2);
        case "RSHIFT" -> wire1 >> wire2;
        case "NOT" -> 0xFFFF & ~wire2;
        default -> throw new IllegalArgumentException(circut[1]);
        };
        wired.put(circut[3], value);
    }

    private boolean isForwardingWire(Map<String, Integer> wired, String[] circut) {
        return circut[1] == null && wired.containsKey(circut[2]);
    }

    private boolean inputWiresAreSet(Map<String, Integer> wired, String[] circut) {
        return (circut[0] == null || isNumber(circut[0]) || wired.containsKey(circut[0]))
                && (isNumber(circut[2]) || wired.containsKey(circut[2]));
    }

    private static boolean isNumber(String str) {
        return Character.isDigit(str.charAt(0));
    }

    private static int getValue(String key, Map<String, Integer> wired) {
        if (key == null) return 0;
        if (isNumber(key)) return parseInt(key);
        return wired.get(key);
    }

    public int a2() {
        return wireThem(a1()).get("a");
    }
}
