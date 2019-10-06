package com.bottomlord.contest_20191005;

public class Contest_4_1_验证回文字符串III {
    public boolean isValidPalindrome(String s, int k) {
        if (s.length() <= 1) {
            return true;
        }

        if (s.charAt(0) == s.charAt(s.length() - 1)) {
            return isValidPalindrome(s.substring(1, s.length() - 1), k);
        } else {
            if (k == 0) {
                return false;
            } else {
                return isValidPalindrome(s.substring(1), k - 1) || isValidPalindrome(s.substring(0, s.length() - 1), k - 1);
            }
        }
    }
}
