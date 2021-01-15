package de.madjosz.adventofcode.y2015;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class Day19 {

    private final Map<String, List<String>> replacements;
    private final String molecule;

    Day19(List<String> input, String molecule) {
        this.replacements = parseReplacements(input);
        this.molecule = molecule;
    }

    public Day19(List<String> input) {
        this(input, input.get(input.size() - 1));
    }

    private static Map<String, List<String>> parseReplacements(List<String> input) {
        Map<String, List<String>> replacements = new LinkedHashMap<>();
        for (String line : input) {
            if (line.isBlank()) break;
            String[] parts = line.split(" => ");
            replacements.computeIfAbsent(parts[0], k -> new ArrayList<>()).add(parts[1]);
        }
        return replacements;
    }

    public int a1() {
        Set<String> createdMolecules = new HashSet<>();
        for (Entry<String, List<String>> entry : replacements.entrySet()) {
            String key = entry.getKey();
            int length = key.length();
            int index = 0;
            while ((index = molecule.indexOf(key, index)) != -1) {
                for (String replacement : entry.getValue())
                    createdMolecules.add(replace(index, length, replacement));
                ++index;
            }
        }
        return createdMolecules.size();
    }

    private String replace(int position, int length, String replacement) {
        return molecule.substring(0, position) + replacement + molecule.substring(position + length);
    }

    public int a2() {
        // this does not really return the fewest steps for all possible inputs
        return minimumSteps(molecule, invertReplacements());
    }

    private int minimumSteps(String str, Map<String, String> inverted) {
        int count = 0;
        while (!"e".equals(str)) {
            for (var entry : inverted.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if ("e".equals(value) && !key.equals(str)) continue;
                int length = key.length();
                int index = 0;
                while ((index = str.indexOf(key, index)) != -1) {
                    str = replace(str, index, length, value);
                    ++count;
                    ++index;
                }
            }
        }
        return count;
    }

    private String replace(String str, int position, int length, String replacement) {
        return str.substring(0, position) + replacement + str.substring(position + length);
    }

    private Map<String, String> invertReplacements() {
        Map<String, String> inverted = new LinkedHashMap<>();
        for (var entry : replacements.entrySet())
            for (String replacement : entry.getValue())
                inverted.put(replacement, entry.getKey());
        return inverted;
    }
}
