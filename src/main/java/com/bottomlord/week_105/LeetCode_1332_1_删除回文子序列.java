package com.bottomlord.week_105;

public class LeetCode_1332_1_删除回文子序列 {
    public int removePalindromeSub(String s) {
        if (s.length() == 0) {
            return 0;
        }

        for (int start = 0, end = s.length() - 1; start < end; start++, end--) {
            if (s.charAt(start) == s.charAt(end)) {
                return 2;
            }
        }

        return 1;
    }
}
