package de.madjosz.adventofcode.y2020;

import static java.util.stream.Collectors.joining;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;


public class Day19 {

    private final List<String> lines;

    public Day19(List<String> input) {
        this.lines = input;
    }

    private static final Pattern number = Pattern.compile("\\d");

    private Map<Integer, String> parseRules() {
        Map<Integer, String> rules = parseRawRules();
        Set<Integer> done = new HashSet<>();
        while (!done.contains(0))
            replaceFinishedRules(rules, done);
        return rules;
    }

    private void replaceFinishedRules(Map<Integer, String> rules, Set<Integer> done) {
        for (Entry<Integer, String> e : rules.entrySet()) {
            Integer i = e.getKey();
            if (done.contains(i)) continue;
            String line = e.getValue();
            if (!number.matcher(line).find()) {
                done.add(i);
                finishRule(rules, done, i, line);
            }
        }
    }

    private void finishRule(Map<Integer, String> rules, Set<Integer> done, Integer i, String line) {
        String rule = line.replaceAll("[\" ]", "");
        rules.put(i, rule);
        Pattern nr = Pattern.compile("\\b" + i + "\\b");
        for (Entry<Integer, String> e2 : rules.entrySet()) {
            if (done.contains(e2.getKey())) continue;
            Matcher m = nr.matcher(e2.getValue());
            if (m.find()) rules.put(e2.getKey(), m.replaceAll("(?:" + rule + ")"));
        }
    }

    private Map<Integer, String> parseRawRules() {
        Map<Integer, String> rules = new LinkedHashMap<>();
        for (String line : lines) {
            if (line.isBlank()) break;
            String[] part = line.split(": ");
            rules.put(Integer.valueOf(part[0]), part[1]);
        }
        return rules;
    }

    public int a1() {
        Pattern rule = Pattern.compile(parseRules().get(0));
        return (int) lines.stream().skip(lines.indexOf("") + 1).filter(rule.asMatchPredicate()).count();
    }

    public int a2() {
        Map<Integer, String> rules = parseRules();
        // 0: 42+ 11
        // 11: 42 31 | 42 11 31
        String r42 = "(" + rules.get(42) + ")";
        String r31 = "(" + rules.get(31) + ")";
        String repeat = IntStream.rangeClosed(1, 5)
                .mapToObj(j -> r42.repeat(j) + r31.repeat(j))
                .collect(joining("|", "(", ")"));
        Pattern rule = Pattern.compile(r42 + "+" + repeat);
        return (int) lines.stream().filter(rule.asMatchPredicate()).count();
    }
}
