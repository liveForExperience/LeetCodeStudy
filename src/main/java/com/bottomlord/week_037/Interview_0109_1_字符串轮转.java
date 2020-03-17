package com.bottomlord.week_037;

import java.util.Objects;

/**
 * @author ThinkPad
 * @date 2020/3/17 8:26
 */
public class Interview_0109_1_字符串轮转 {
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        if (Objects.equals(s1, "") && Objects.equals(s2, "")) {
            return true;
        }

        int len2 = s2.length();
        for (int i = 0; i < len2; i++) {
            if (s1.charAt(0) == s2.charAt(i)) {
                if (Objects.equals(s1, String.valueOf(s2.toCharArray(), i, len2 - i) + String.valueOf(s2.toCharArray(), 0, len2 - i))) {
                    return true;
                }
            }
        }

        return false;
    }
}
