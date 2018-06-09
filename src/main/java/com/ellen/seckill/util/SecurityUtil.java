package com.ellen.seckill.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * a util class to handle security-related operations
 */
public class SecurityUtil {

    static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * encrypt str using BCrypt strong hashing function
     *
     * @param str
     * @return encrypted str
     */
    public static String encrypt(String str) {
        return passwordEncoder.encode(str);
    }

    /**
     * verify if encryptStr matches rawStr after it is too encrypted
     *
     * @param rawStr
     * @param encryptStr
     * @return true if match
     */
    public static boolean match(String rawStr, String encryptStr) {

        return passwordEncoder.matches(rawStr, encryptStr);
    }

    // TODO generate api_key
}
