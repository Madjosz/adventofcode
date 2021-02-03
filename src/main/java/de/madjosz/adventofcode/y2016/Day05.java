package de.madjosz.adventofcode.y2016;

import de.madjosz.adventofcode.data.MD5;
import java.security.MessageDigest;


public class Day05 {

    private final String doorId;
    private static final MessageDigest md5 = MD5.get();

    public Day05(String input) {
        this.doorId = input;
    }

    public String a1() {
        StringBuilder code = new StringBuilder(8);
        int index = 0;
        while (code.length() < 8) {
            byte[] digest = md5.digest((doorId + index).getBytes());
            if (checkHead(digest)) code.append(Integer.toHexString(digest[2] & 0xF));
            ++index;
        }
        return code.toString();
    }

    public String a2() {
        int[] code = new int[8];
        for (int i = 0; i < code.length; ++i)
            code[i] = -1;
        for (int index = 0;; ++index) {
            byte[] digest = md5.digest((doorId + index).getBytes());
            if (checkHead(digest) && checkPosition(digest, code) && allFilled(code))
                return new String(code, 0, code.length);
        }
    }

    private boolean checkHead(byte[] digest) {
        return digest[0] == 0 && digest[1] == 0 && digest[2] >= 0 && digest[2] < 16;
    }

    private boolean checkPosition(byte[] digest, int[] code) {
        int pos = digest[2];
        if (pos < 8 && code[pos] == -1) {
            int number = digest[3];
            if (number < 0) number += 256;
            code[pos] = Integer.toHexString(number / 16).charAt(0);
            return true;
        }
        return false;
    }

    private boolean allFilled(int[] code) {
        for (int i = 0; i < code.length; ++i)
            if (code[i] == -1) return false;
        return true;
    }
}
