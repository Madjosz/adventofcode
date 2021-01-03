package de.madjosz.adventofcode.y2020;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day04 {

    private final List<String> passports;

    public Day04(List<String> input) {
        this.passports = input;
    }

    private static final Set<String> requiredFields = Set.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
    private static final Pattern p = Pattern.compile("(\\w{3}):([#\\w]+)");
    private static final Map<String, Predicate<String>> validators = Map.of(//
            "byr", predicate("19[2-9]\\d|200[0-2]"), //
            "iyr", predicate("201\\d|2020"), //
            "eyr", predicate("202\\d|2030"), //
            "hgt", predicate("(1[5-8]\\d|19[0-3])cm|(59|6\\d|7[0-6])in"), //
            "hcl", predicate("#[0-9a-f]{6}"), //
            "ecl", predicate("amb|blu|brn|gry|grn|hzl|oth"), //
            "pid", predicate("\\d{9}"));

    private static Predicate<String> predicate(String regex) {
        return Pattern.compile(regex).asMatchPredicate();
    }

    public int a1() {
        return countValidPasses((f, v) -> true);
    }

    public int a2() {
        return countValidPasses((f, v) -> validators.getOrDefault(f, s -> false).test(v));
    }

    private int countValidPasses(BiPredicate<String, String> validator) {
        Set<String> currentPass = new HashSet<>();
        int valid = 0;
        for (String line : passports) {
            if (line.isBlank()) {
                if (currentPass.containsAll(requiredFields)) ++valid;
                currentPass.clear();
            } else {
                Matcher m = p.matcher(line);
                while (m.find())
                    if (validator.test(m.group(1), m.group(2))) currentPass.add(m.group(1));
            }
        }
        if (currentPass.containsAll(requiredFields)) ++valid;
        return valid;
    }

}
