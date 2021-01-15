package de.madjosz.adventofcode.y2015;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day21 {

    private final int bossHP;
    private final int bossBaseDmg;
    private final int bossBaseArmor;

    private final List<int[]> weapons;
    private final List<int[]> armor;
    private final List<int[]> rings;

    private static final Pattern ITEM = Pattern.compile("^[A-Za-z0-9+ ]+ +(\\d+) +(\\d+) +(\\d+) *$");

    public Day21(List<String> input) {
        this.bossHP = readStat(input.get(0));
        this.bossBaseDmg = readStat(input.get(1));
        this.bossBaseArmor = readStat(input.get(2));

        int itemstart = findLine(input, "Weapons", 0);
        this.weapons = parseItems(input, itemstart + 1);
        this.armor = parseItems(input, findLine(input, "Armor:", itemstart));
        this.rings = parseItems(input, findLine(input, "Rings:", itemstart));
    }

    private static int readStat(String line) {
        return parseInt(line.split(": ")[1]);
    }

    private List<int[]> parseItems(List<String> input, int firstLine) {
        List<int[]> items = new ArrayList<>();
        for (int i = firstLine; i < input.size(); ++i) {
            String line = input.get(i);
            if (line.isBlank()) break;
            Matcher m = ITEM.matcher(line);
            if (m.find()) items.add(new int[] { parseInt(m.group(1)), parseInt(m.group(2)), parseInt(m.group(3)) });
        }
        return items;
    }

    private int findLine(List<String> input, String string, int start) {
        for (int i = start; i < input.size(); ++i)
            if (input.get(i).startsWith(string)) return i;
        throw new NoSuchElementException();
    }

    public int a1() {
        return a1(100);
    }

    int a1(int playerHP) {
        return fight(playerHP)[0];
    }

    private int[] fight(int playerHP) {
        int[] extremeCost = new int[] { Integer.MAX_VALUE, 0 };
        for (int w = 0; w < weapons.size(); ++w) {
            for (int a = -1; a < armor.size(); ++a) {
                for (int r1 = -1; r1 < rings.size(); ++r1) {
                    for (int r2 = -1; r2 < rings.size(); ++r2) {
                        if (r2 != -1 && r2 == r1) continue;
                        int playerDmg = Math.max(1, getStat(w, a, r1, r2, 1) - bossBaseArmor);
                        int bossDmg = Math.max(1, bossBaseDmg - getStat(w, a, r1, r2, 2));
                        boolean won = neededRounds(bossHP, playerDmg) <= neededRounds(playerHP, bossDmg);
                        if (won) extremeCost[0] = Math.min(extremeCost[0], getStat(w, a, r1, r2, 0));
                        else extremeCost[1] = Math.max(extremeCost[1], getStat(w, a, r1, r2, 0));
                    }
                }
            }
        }
        return extremeCost;
    }

    private int neededRounds(int hp, int dmg) {
        return (hp - 1) / dmg + 1;
    }

    private int getStat(int w, int a, int r1, int r2, int idx) {
        int sum = weapons.get(w)[idx];
        if (a != -1) sum += armor.get(a)[idx];
        if (r1 != -1) sum += rings.get(r1)[idx];
        if (r2 != -1) sum += rings.get(r2)[idx];
        return sum;
    }

    public int a2() {
        return a2(100);
    }

    int a2(int playerHP) {
        return fight(playerHP)[1];
    }

}
