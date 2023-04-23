package com.bottomlord.week_197;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-04-23 11:18:18
 */
public class LeetCode_1105_1_填充书架 {
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1000000);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int width = 0, maxHeight = 0;
            for (int j = i; j > 0; j--) {
                width += books[j - 1][0];
                if (width > shelfWidth) {
                    break;
                }

                maxHeight = Math.max(maxHeight, books[j - 1][1]);
                dp[i] = Math.min(dp[i], dp[j - 1] + maxHeight);
            }
        }
        return dp[n];
    }
}
