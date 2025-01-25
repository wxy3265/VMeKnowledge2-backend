package com.vmeknowledge.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * 使用BCrypt进行密码加密和验证
 */
public class BCryptPasswordUtil {

    /**
     * 对明文密码进行加密
     * @param plainPassword 明文密码
     * @return 加密后的密码
     */
    public static String encryptPassword(String plainPassword) {
        // 生成盐值并对密码进行加密
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    /**
     * 验证输入的明文密码与加密后的密码是否匹配
     * @param plainPassword 明文密码
     * @param hashedPassword 加密后的密码
     * @return 若匹配返回 true，反之返回 false
     */
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        // 验证密码是否匹配
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}