package com.bottomlord.week_203;

/**
 * @author chen yue
 * @date 2023-05-29 09:29:38
 */
public class LeetCode_2697_1_字典序最小回文串 {
    public String makeSmallestPalindrome(String s) {
        int n = s.length(), head = 0, tail = n - 1;
        char[] cs = s.toCharArray();
        while (head <= tail) {
            char a = cs[head], b = cs[tail];
            if (a != b) {
                cs[head] = cs[tail] = a < b ? a : b;
            }

            head++;
            tail--;
        }

        return new String(cs);
    }
}
