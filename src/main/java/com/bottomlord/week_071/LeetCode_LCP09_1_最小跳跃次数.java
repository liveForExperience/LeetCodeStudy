package com.bottomlord.week_071;

/**
 * @author ChenYue
 * @date 2020/11/16 17:24
 */
public class LeetCode_LCP09_1_最小跳跃次数 {
    public int minJump(int[] jump) {
        int len = jump.length;
        int[] dp = new int[len];
        dp[len - 1] = 1;

        for (int i = len - 2; i >= 0; i--) {
            dp[i] = i + jump[i] >= len ? 1 : dp[i + jump[i]] + 1;

            for (int j = i + 1; j < len && dp[i] + 1 <= dp[j]; j++) {
                dp[j] = dp[i] + 1;
            }
        }

        return dp[0];
    }
}
