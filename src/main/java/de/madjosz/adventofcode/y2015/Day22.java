package de.madjosz.adventofcode.y2015;

import static java.lang.Integer.parseInt;

import java.util.List;


public class Day22 {

    private final int bossHitpoints;
    private final int bossDamage;

    public Day22(List<String> input) {
        this.bossHitpoints = readStat(input.get(0));
        this.bossDamage = readStat(input.get(1));
    }

    private static int readStat(String line) {
        return parseInt(line.split(": ")[1]);
    }

    private static List<Spell> spells = List.of(new MagicMissle(), new Drain(), new Shield(), new Poison(),
            new Recharge());

    private static class Fight implements Cloneable {

        private int bossHP;
        private final int bossDmg;
        protected int playerHP;
        private int mana;
        private int usedMana;
        private int shieldCD;
        private int poisonCD;
        private int rechargeCD;

        public Fight(int bossHP, int bossDmg, int playerHP, int mana) {
            this.bossHP = bossHP;
            this.bossDmg = bossDmg;
            this.playerHP = playerHP;
            this.mana = mana;
        }

        private Fight(Fight fight) {
            this.bossHP = fight.bossHP;
            this.bossDmg = fight.bossDmg;
            this.playerHP = fight.playerHP;
            this.mana = fight.mana;
            this.usedMana = fight.usedMana;
            this.shieldCD = fight.shieldCD;
            this.poisonCD = fight.poisonCD;
            this.rechargeCD = fight.rechargeCD;
        }

        private void effects() {
            if (shieldCD > 0) --shieldCD;
            if (poisonCD > 0) {
                bossHP -= 3;
                --poisonCD;
            }
            if (rechargeCD > 0) {
                mana += 101;
                --rechargeCD;
            }
        }

        protected void turn(Spell spell) {
            effects();
            spell.cast(this);
            if (usedMana >= minMana) throw new IllegalStateException();
            if (bossHP <= 0) {
                minMana = usedMana;
                return;
            }
            effects();
            if (bossHP <= 0) {
                minMana = usedMana;
                return;
            }
            bossAttack();
            if (playerHP <= 0) throw new IllegalStateException();
            nextRound();
        }

        private void bossAttack() {
            playerHP -= Math.max(1, bossDmg - (shieldCD > 0 ? 7 : 0));
        }

        public void nextRound() {
            for (int i = 0; i < spells.size(); ++i) {
                try {
                    Fight fight = copy();
                    fight.turn(spells.get(i));
                } catch (IllegalStateException e) {}
            }
        }

        protected Fight copy() {
            return new Fight(this);
        }
    }

    private abstract static class Spell {

        private final int manaCost;

        protected Spell(int manaCost) {
            this.manaCost = manaCost;
        }

        public void cast(Fight fight) {
            fight.mana -= manaCost;
            fight.usedMana += manaCost;
            castSpell(fight);
            if (fight.mana < 0) throw new IllegalStateException();
        }

        protected abstract void castSpell(Fight fight);

        protected void checkCD(int cooldown) {
            if (cooldown > 0) throw new IllegalStateException();
        }
    }

    private static class MagicMissle extends Spell {

        public MagicMissle() {
            super(53);
        }

        @Override
        protected void castSpell(Fight fight) {
            fight.bossHP -= 4;
        }
    }

    private static class Drain extends Spell {

        public Drain() {
            super(73);
        }

        @Override
        protected void castSpell(Fight fight) {
            fight.bossHP -= 2;
            fight.playerHP += 2;
        }
    }

    private static class Shield extends Spell {

        public Shield() {
            super(113);
        }

        @Override
        protected void castSpell(Fight fight) {
            checkCD(fight.shieldCD);
            fight.shieldCD = 6;
        }
    }

    private static class Poison extends Spell {

        public Poison() {
            super(173);
        }

        @Override
        protected void castSpell(Fight fight) {
            checkCD(fight.poisonCD);
            fight.poisonCD = 6;
        }
    }

    private static class Recharge extends Spell {

        public Recharge() {
            super(229);
        }

        @Override
        protected void castSpell(Fight fight) {
            checkCD(fight.rechargeCD);
            fight.rechargeCD = 5;
        }
    }

    public int a1() {
        return a1(50, 500);
    }

    private static int minMana;

    int a1(int playerHP, int mana) {
        minMana = Integer.MAX_VALUE;
        new Fight(bossHitpoints, bossDamage, playerHP, mana).nextRound();
        return minMana;
    }

    private static class FightHard extends Fight {

        public FightHard(int bossHP, int bossDmg, int playerHP, int mana) {
            super(bossHP, bossDmg, playerHP, mana);
        }

        private FightHard(FightHard fight) {
            super(fight);
        }

        @Override
        protected void turn(Spell spell) {
            --playerHP;
            if (playerHP <= 0) throw new IllegalStateException();
            super.turn(spell);
        }

        @Override
        protected Fight copy() {
            return new FightHard(this);
        }
    }

    public int a2() {
        return a2(50, 500);
    }

    int a2(int playerHP, int mana) {
        minMana = Integer.MAX_VALUE;
        new FightHard(bossHitpoints, bossDamage, playerHP, mana).nextRound();
        return minMana;
    }
}
