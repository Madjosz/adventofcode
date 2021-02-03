package de.madjosz.adventofcode.y2016;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Predicate;


public class Day10 {

    private final List<String> input;

    public Day10(List<String> input) {
        this.input = input;
    }

    private interface Out {

        void setValue(int value);
    }

    private static class Output implements Out {

        private Integer value;

        @Override
        public void setValue(int value) {
            this.value = value;
        }
    }

    private static class Bot implements Out {

        private Integer value1;
        private Integer value2;
        Out high;
        Out low;

        @Override
        public void setValue(int value) {
            if (this.value1 == null) this.value1 = value;
            else {
                this.value2 = value;
                transmit();
            }
        }

        public void transmit() {
            if (high == null || low == null || value1 == null || value2 == null) return;
            if (value1 > value2) {
                high.setValue(value1);
                low.setValue(value2);
            } else {
                high.setValue(value2);
                low.setValue(value1);
            }
        }
    }

    public int a1() {
        return a1(61, 17);
    }

    private record Machinery(Map<String, Bot> bots, Map<String, Output> outputs) {}

    int a1(int cmp1, int cmp2) {
        Map<String, Bot> bots = parseInput().bots();
        return Integer.parseInt(bots.entrySet().stream().filter(find(cmp1, cmp2)).findAny().orElseThrow().getKey());
    }

    private Predicate<? super Entry<String, Bot>> find(int cmp1, int cmp2) {
        return e -> {
            Bot bot = e.getValue();
            return (bot.value1 == cmp1 && bot.value2 == cmp2) || (bot.value2 == cmp1 && bot.value1 == cmp2);
        };
    }

    private Machinery parseInput() {
        Map<String, Bot> bots = new LinkedHashMap<>();
        Map<String, Output> outputs = new TreeMap<>();
        for (String line : input) {
            String[] parts = line.split(" ");
            switch (parts[0]) {
            case "value":
                bots.computeIfAbsent(parts[5], k -> new Bot()).setValue(Integer.parseInt(parts[1]));
                break;
            case "bot":
                Bot bot = bots.computeIfAbsent(parts[1], k -> new Bot());
                if ("output".equals(parts[5])) bot.low = outputs.computeIfAbsent(parts[6], k -> new Output());
                else bot.low = bots.computeIfAbsent(parts[6], k -> new Bot());
                if ("output".equals(parts[10])) bot.high = outputs.computeIfAbsent(parts[11], k -> new Output());
                else bot.high = bots.computeIfAbsent(parts[11], k -> new Bot());
                bot.transmit();
                break;
            default:
                throw new IllegalArgumentException(line);
            }
        }
        return new Machinery(bots, outputs);
    }

    public int a2() {
        Map<String, Output> outputs = parseInput().outputs();
        return outputs.get("0").value * outputs.get("1").value * outputs.get("2").value;
    }
}
