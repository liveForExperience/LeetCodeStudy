package com.bottomlord.week_026;

/**
 * @author ThinkPad
 * @date 2019/12/30 8:53
 */
public class LeetCode_1035_1_不相交的线 {
    public int maxUncrossedLines(int[] A, int[] B) {
        int a = A.length, b = B.length;
        int[][] dp = new int[a + 1][b + 1];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                dp[i + 1][j + 1] = A[i] == B[j] ? dp[i][j] + 1 : Math.max(dp[i][j + 1], dp[j + 1][j]);
            }
        }
        return dp[a][b];
    }
}
