package com.bottomlord.week_138;

/**
 * @author chen yue
 * @date 2022-03-02 09:26:07
 */
public class LeetCode_564_1_寻找最近的回文数 {
    public String nearestPalindromic(String n) {
        long len = 1;
        while (true) {
            String left = String.valueOf(Long.parseLong(n) - len);
            if (check(left)) {
                return left;
            }

            String right = String.valueOf(Long.parseLong(n) + len);
            if (check(right)) {
                return right;
            }

            len++;
        }
    }

    private boolean check(String n) {
        int l = 0, r = n.length() - 1;
        while (l < r) {
            if (n.charAt(l++) != n.charAt(r--)) {
                return false;
            }
        }
        return true;
    }
}
