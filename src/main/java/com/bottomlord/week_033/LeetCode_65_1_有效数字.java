package com.bottomlord.week_033;

/**
 * @author ThinkPad
 * @date 2020/2/20 20:41
 */
public class LeetCode_65_1_有效数字 {
    public static boolean isNumber(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return false;
        }

        int index = 0;
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            index++;
        }

        int num = 0, dot = 0;
        while (index < s.length()) {
            if (s.charAt(index) >= '0' && s.charAt(index) <= '9') {
                index++;
                num++;
            } else if (s.charAt(index) == '.') {
                index++;
                dot++;
            } else {
                break;
            }
        }

        if (dot > 1 || num == 0) {
            return false;
        }

        if (index == s.length()) {
            return true;
        }

        if (s.charAt(index++) == 'e') {
            if (index == s.length()) {
                return false;
            }

            if (s.charAt(index) == '+' || s.charAt(index) == '-') {
                index++;
                if (index == s.length()) {
                    return false;
                }
            }

            while (index < s.length() && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
                index++;
            }

            return index == s.length();
        }

        return false;
    }
}
