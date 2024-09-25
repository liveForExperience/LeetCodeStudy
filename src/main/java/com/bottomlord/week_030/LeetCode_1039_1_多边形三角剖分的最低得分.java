package com.bottomlord.week_030;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/1/27 13:45
 */
public class LeetCode_1039_1_多边形三角剖分的最低得分 {
    public int minScoreTriangulation(int[] A) {
        int len = A.length;
        int[][] dp = new int[len][len];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        for (int i = 0; i < len; i++) {
            dp[i][(i + 1) % len] = 0;
        }
        for (int l = 2; l < len; l++) {
            for (int i = 0; i < len; i++) {
                int j = (i + l) % len;
                for (int k = (i + 1) % len; k != j; k = (k + 1) % len) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[k] * A[j]);
                }
            }
        }
        return dp[0][len - 1];
    }
}
