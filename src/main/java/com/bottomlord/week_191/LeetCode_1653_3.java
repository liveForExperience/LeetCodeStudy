package com.bottomlord.week_191;

/**
 * @author chen yue
 * @date 2023-03-06 19:38:14
 */
public class LeetCode_1653_3 {
    public int minimumDeletions(String s) {
        int dp = 0, b = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') {
                dp = Math.min(dp + 1, b);
            } else {
                b++;
            }
        }

        return dp;
    }
}