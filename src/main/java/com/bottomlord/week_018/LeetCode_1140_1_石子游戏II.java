package com.bottomlord.week_018;

public class LeetCode_1140_1_石子游戏II {
    public int stoneGameII(int[] piles) {
        int len = piles.length;
        int[] sum = new int[len];
        Integer[][] memo = new Integer[len + 1][len + 1];
        sum[len - 1] = piles[len - 1];
        for (int i = piles.length - 2; i >= 0; i--) {
            sum[i] = sum[i + 1] + piles[i];
        }
        return dp(0, 1, sum, len, memo);
    }

    private int dp(int i, int j, int[] sum, int len, Integer[][] memo) {
        if (i >= len) {
            return 0;
        }

        if (i + 2 * j >= len) {
            return sum[i];
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int best = 0;
        for (int k = 1; k <= 2 * j; k++) {
            best = Math.max(best, sum[i] - dp(i + k, Math.max(k, j), sum, len, memo));
        }

        memo[i][j] = best;
        return best;
    }
}