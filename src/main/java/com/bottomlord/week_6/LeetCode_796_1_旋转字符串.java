package com.bottomlord.week_6;

public class LeetCode_796_1_旋转字符串 {
    public boolean rotateString(String A, String B) {
        if (A.equals(B)) {
            return true;
        }

        String str = A;
        while (true) {
            str = rotate(str);
            if (A.equals(str)) {
                return false;
            }

            if (B.equals(str)) {
                return true;
            }
        }
    }

    private String rotate(String str) {
        char[] cs = str.toCharArray();
        for (int i = 0; i < str.length() - 1; i++) {
            cs[i] ^= cs[i + 1];
            cs[i + 1] ^= cs[i];
            cs[i] ^= cs[i + 1];
        }
        return new String(cs);
    }
}