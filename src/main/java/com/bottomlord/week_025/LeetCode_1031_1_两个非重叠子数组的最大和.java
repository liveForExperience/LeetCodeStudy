package com.bottomlord.week_025;

/**
 * @author ThinkPad
 * @date 2019/12/25 16:06
 */
public class LeetCode_1031_1_两个非重叠子数组的最大和 {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int[][] dp = new int[A.length][4];
        int len = A.length, pre = 0, max = 0;
        for (int i = 0; i < L; i++) {
            pre += A[i];
        }
        max = pre;
        dp[L - 1][0] = pre;
        for (int i = L; i < len; i++) {
            pre -= A[i - L];
            pre += A[i];
            max = Math.max(pre, max);
            dp[i][0] = max;
        }

        pre = 0;
        for (int i = 0; i < M; i++) {
            pre += A[i];
        }
        max = pre;
        dp[M - 1][1] = pre;
        for (int i = M; i < len; i++) {
            pre -= A[i - M];
            pre += A[i];
            max = Math.max(max, pre);
            dp[i][1] = max;
        }

        pre = 0;
        for (int i = len - 1; i >= len - L; i--) {
            pre += A[i];
        }
        max = pre;
        dp[len - L][2] = pre;
        for (int i = len - L - 1; i >= 0; i--) {
            pre -= A[i + L];
            pre += A[i];
            max = Math.max(max, pre);
            dp[i][2] = max;
        }

        pre = 0;
        for (int i = len - 1; i >= len - M; i--) {
            pre += A[i];
        }
        max = pre;
        dp[len - M][3] = pre;
        for (int i = len - M - 1; i >= 0; i--) {
            pre -= A[i + M];
            pre += A[i];
            max = Math.max(max, pre);
            dp[i][3] = max;
        }

        int ans = 0;
        for (int i = L; i <= len - M; i++) {
            ans = Math.max(ans, dp[i - 1][0] + dp[i][3]);
        }

        for (int i = M; i <= len - L; i++) {
            ans = Math.max(ans, dp[i - 1][1] + dp[i][2]);
        }

        return ans;
    }
}
