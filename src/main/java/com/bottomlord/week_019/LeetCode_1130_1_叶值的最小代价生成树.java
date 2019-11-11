package com.bottomlord.week_019;

public class LeetCode_1130_1_叶值的最小代价生成树 {
    public int mctFromLeafValues(int[] arr) {
        int len = arr.length;
        int[][] maxValue = new int[len][len], dp = new int[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int max = 0;
                for (int k = i; k <= j; k++) {
                    max = Math.max(max, arr[k]);
                }
                maxValue[i][j] = max;
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < len; i++) {
            dp[i][i] = 0;
        }

        for (int range = 1; range < len; range++) {
            for (int start = 0; start < len - range; start++) {
                for (int mid = start; mid < start + range; mid++) {
                    dp[start][start + range] = Math.min(dp[start][start + range], dp[start][mid] + dp[mid + 1][start + range] + maxValue[start][mid] * maxValue[mid + 1][start + range]);
                }
            }
        }

        return dp[0][len - 1];
    }
}
