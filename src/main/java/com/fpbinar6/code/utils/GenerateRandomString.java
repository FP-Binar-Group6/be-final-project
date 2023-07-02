package com.fpbinar6.code.utils;

import java.util.Random;

public class GenerateRandomString {
    
    public static String generateRandomString(int length) {
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allowedCharacters.length());
            sb.append(allowedCharacters.charAt(randomIndex));
        }
        return sb.toString();
    }
}
