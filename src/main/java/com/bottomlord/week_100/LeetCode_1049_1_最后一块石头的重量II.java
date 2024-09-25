package com.bottomlord.week_100;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/6/8 8:16
 */
public class LeetCode_1049_1_最后一块石头的重量II {
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum(), len = stones.length, target = sum / 2;
        int[] dp = new int[target + 1];
        for (int stone : stones) {
            for (int i = target; i >= stone; i--) {
                dp[i] = Math.max(dp[i], dp[i - stone] + stone);
            }
        }

        return sum - 2 * dp[target];
    }
}
