package com.bottomlord.week_029;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/1/22 15:55
 */
public class LeetCode_1105_1_填充书架 {
    public int minHeightShelves(int[][] books, int shelf_width) {
        int[] dp = new int[books.length + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[books.length] = 0;

        for (int i = books.length - 1; i >= 0; i--) {
            int height = 0, leftWeight = shelf_width;
            for (int j = i; j < books.length && leftWeight - books[j][0] >= 0; j++) {
                height = Math.max(height, books[j][1]);
                leftWeight = leftWeight - books[j][0];
                dp[i] = Math.min(dp[i], height + dp[j + 1]);
            }
        }

        return dp[0];
    }
}
