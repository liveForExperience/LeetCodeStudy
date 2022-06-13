package com.bottomlord.week_153;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-06-13 13:00:51
 */
public class LeetCode_6095_1_强密码检验器II {
    public boolean strongPasswordCheckerII(String password) {
        boolean hasLower = false, hasUpper = false, hasNum = false, hasSpecial = false;
        int n = password.length();
        if (n < 8) {
            return false;
        }

        Set<Character> specials = new HashSet<>();
        String specialStr = "!@#$%^&*()-+";
        char[] cs = specialStr.toCharArray();

        for (char c : cs) {
            specials.add(c);
        }

        for (int i = 0; i < n; i++) {
            if (i != n - 1) {
                if (password.charAt(i) == password.charAt(i + 1)) {
                    return false;
                }
            }

            char c = password.charAt(i);

            if (Character.isDigit(c)) {
                hasNum = true;
            }

            if (Character.isLowerCase(c)) {
                hasLower = true;
            }

            if (Character.isUpperCase(c)) {
                hasUpper = true;
            }

            if (specials.contains(c)) {
                hasSpecial = true;
            }
        }

        return hasNum && hasUpper && hasLower && hasSpecial;
    }
}
