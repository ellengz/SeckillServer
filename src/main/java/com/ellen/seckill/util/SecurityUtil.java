package com.ellen.seckill.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

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

    /**
     * generate a UUID
     *
     * @return str
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * use UUID as user token
     *
     * @return user token
     */
    public static final String getUserToken() {
        return encrypt(getUUID());
    }
}
