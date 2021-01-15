package de.madjosz.adventofcode.y2015;

public class Day11 {

    private final String oldpw;

    public Day11(String input) {
        this.oldpw = input;
    }

    public String a1() {
        return nextValidPw(oldpw.toCharArray());
    }

    private static String nextValidPw(char[] pw) {
        if (!cleanRule2(pw)) increment(pw);
        while (failsRule1(pw) || failsRule3(pw))
            increment(pw);
        return String.valueOf(pw);
    }

    private static boolean failsRule1(char[] pw) {
        for (int i = 0; i < pw.length - 2; ++i)
            if (pw[i] + 1 == pw[i + 1] && pw[i + 1] == pw[i + 2] - 1) return false;
        return true;
    }

    private static boolean cleanRule2(char[] pw) {
        boolean forbiddenFound = false;
        for (int i = 0; i < pw.length; ++i) {
            if (forbiddenFound) pw[i] = 'a';
            else {
                char c = pw[i];
                if (c == 'i' || c == 'o' || c == 'l') {
                    forbiddenFound = true;
                    pw[i] = increment(c);
                }
            }
        }
        return forbiddenFound;
    }

    private static boolean failsRule3(char[] pw) {
        int found = 0;
        for (int i = 0; i < pw.length - 1; ++i)
            if (pw[i] == pw[i + 1]) {
                ++found;
                ++i;
            }
        return found < 2;
    }

    private static void increment(char[] pw) {
        for (int i = pw.length - 1; i >= 0; --i) {
            pw[i] = increment(pw[i]);
            if (pw[i] != 'a') return;
        }
    }

    private static char increment(char c) {
        switch (c) {
        case 'h', 'n', 'k':
            return (char) (c + 2);
        case 'z':
            return 'a';
        default:
            return (char) (c + 1);
        }
    }

    public String a2() {
        return nextValidPw(a1().toCharArray());
    }
}
