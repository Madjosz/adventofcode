package de.madjosz.adventofcode.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public final class MD5 {

    private MD5() {}

    public static MessageDigest get() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
