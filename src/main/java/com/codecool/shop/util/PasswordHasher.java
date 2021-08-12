package com.codecool.shop.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class PasswordHasher {

    private static SecureRandom random;
    private static byte[] salt;

    static {
        random = new SecureRandom();
        salt = new byte[16];
        random.nextBytes(salt);
    }

    public static boolean validatePassword(String password, byte[] hashed_password) {
        byte[] hash = generateHash(password);
        return Arrays.equals(hash, hashed_password);
    }

    public static byte[] generateHash(String password) {

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

}
