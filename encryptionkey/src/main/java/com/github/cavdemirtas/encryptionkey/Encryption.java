package com.github.cavdemirtas.encryptionkey;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encryption {
    private final String key;

    public Encryption(String key) {
        this.key = key;
    }

    public String encrypt(String text) {
        byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);

        byte[] encryptedBytes = new byte[textBytes.length];
        for (int i = 0; i < textBytes.length; i++) {
            encryptedBytes[i] = (byte) (textBytes[i] ^ keyBytes[i % keyBytes.length]);
        }
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        return encryptedText;
    }

    public String decrypt(String cipherText) {
        byte[] encryptedBytes = Base64.getDecoder().decode(cipherText);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);

        byte[] decryptedBytes = new byte[encryptedBytes.length];
        for (int i = 0; i < encryptedBytes.length; i++) {
            decryptedBytes[i] = (byte) (encryptedBytes[i] ^ keyBytes[i % keyBytes.length]);
        }
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}