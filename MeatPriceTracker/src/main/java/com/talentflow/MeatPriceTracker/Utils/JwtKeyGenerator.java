package com.talentflow.MeatPriceTracker.Utils;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtKeyGenerator {

    public static void main(String[] args) {
        byte[] key = new byte[64]; // 512 bits for HS512
        new SecureRandom().nextBytes(key);
        String base64Key = Base64.getEncoder().encodeToString(key);
        System.out.println("Your JWT Secret:\n" + base64Key);
    }
}
