package com.bottomlord.week_029;

/**
 * @author ThinkPad
 * @date 2020/1/26 20:30
 */
public class LeetCode_583_3 {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[] dp = new int[len2 + 1];

        for (int i = 0; i <= len1; i++) {
            int[] tmp = new int[len2 + 1];
            for (int j = 0; j <= len2; j++) {
                if (i == 0 || j == 0) {
                    tmp[j] = i + j;
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    tmp[j] = dp[j - 1];
                } else {
                    tmp[j] = 1 + Math.min(dp[j], tmp[j - 1]);
                }
            }
            dp = tmp;
        }

        return dp[len2];
    }
}