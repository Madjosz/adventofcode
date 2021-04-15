package de.madjosz.adventofcode.y2020;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;


public class Day16 {

    private final List<String> lines;

    public Day16(List<String> input) {
        this.lines = input;
    }

    private static final Pattern rule = Pattern.compile("^([a-z ]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)");

    public int a1() {
        List<Closed> rules = parseRules();
        int start = lines.indexOf("nearby tickets:") + 1;
        return lines.stream()
                .skip(start)
                .flatMap(l -> Arrays.stream(l.split(",")))
                .mapToInt(Integer::parseInt)
                .filter(n -> rules.stream().noneMatch(r -> r.contains(n)))
                .sum();
    }

    private List<Closed> parseRules() {
        List<Closed> rules = new ArrayList<>();
        for (String line : lines) {
            if (line.isBlank()) return rules;
            Matcher m = rule.matcher(line);
            m.find();
            rules.add(new Closed(m.group(2), m.group(3)));
            rules.add(new Closed(m.group(4), m.group(5)));
        }
        return rules;
    }

    private record Closed(int min, int max) {

        private Closed(String min, String max) {
            this(Integer.parseInt(min), Integer.parseInt(max));
        }

        public boolean contains(int x) {
            return min <= x && x <= max;
        }
    }

    public long a2() {
        List<int[]> validTickets = getValidTickets();
        String[] fields = new FieldMatcher().match(validTickets);

        int[] myTicket = parseTicket(lines.get(lines.indexOf("your ticket:") + 1));

        return IntStream.range(0, fields.length)
                .filter(i -> fields[i].startsWith("departure"))
                .mapToLong(i -> myTicket[i])
                .reduce(1L, (a, b) -> a * b);

    }

    private class FieldMatcher {

        private final Map<String, List<Closed>> rules2 = parseRules2();
        final String[] fields = new String[rules2.size()];
        private final Map<Integer, List<String>> possibleColumns = new LinkedHashMap<>();

        public FieldMatcher() {
            for (int i = 0; i < rules2.size(); ++i)
                possibleColumns.put(i, new ArrayList<>(rules2.keySet()));
        }

        public String[] match(List<int[]> validTickets) {
            for (int[] ticket : validTickets)
                checkTicketFields(ticket);
            return fields;
        }

        private void checkTicketFields(int[] ticket) {
            for (int i = 0; i < ticket.length; ++i) {
                if (fields[i] == null) checkTicketField(i, ticket[i]);
            }
        }

        private void checkTicketField(int i, int value) {
            List<String> possibles = possibleColumns.get(i);
            dropColumnsIfNoMatch(value, possibles);
            if (possibles.size() == 1) registerColumn(i, possibles.get(0));
        }

        private void dropColumnsIfNoMatch(int value, List<String> possibles) {
            Iterator<String> iter = possibles.iterator();
            while (iter.hasNext()) {
                String col = iter.next();
                if (rules2.get(col).stream().noneMatch(r -> r.contains(value))) iter.remove();
            }
        }

        private void registerColumn(int i, String column) {
            fields[i] = column;
            possibleColumns.values().forEach(l -> l.remove(column));
            checkAllPossibles();
        }

        private void checkAllPossibles() {
            possibleColumns.entrySet()
                    .stream()
                    .filter(e -> e.getValue().size() == 1)
                    .findAny()
                    .ifPresent(e -> registerColumn(e.getKey(), e.getValue().get(0)));
        }

        private Map<String, List<Closed>> parseRules2() {
            Map<String, List<Closed>> rules = new HashMap<>();
            for (String line : lines) {
                if (line.isBlank()) return rules;
                Matcher m = rule.matcher(line);
                m.find();
                rules.put(m.group(1), List.of(new Closed(m.group(2), m.group(3)), new Closed(m.group(4), m.group(5))));
            }
            return rules;
        }
    }

    private List<int[]> getValidTickets() {
        List<Closed> rules = parseRules();
        int start = lines.indexOf("nearby tickets:") + 1;
        return lines.stream()
                .skip(start)
                .map(Day16::parseTicket)
                .filter(t -> Arrays.stream(t).allMatch(i -> rules.stream().anyMatch(r -> r.contains(i))))
                .collect(toList());
    }

    private static int[] parseTicket(String l) {
        return Arrays.stream(l.split(",")).mapToInt(Integer::parseInt).toArray();
    }

}
