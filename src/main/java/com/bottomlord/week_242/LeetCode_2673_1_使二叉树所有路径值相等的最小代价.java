package com.bottomlord.week_242;

/**
 * @author chen yue
 * @date 2024-02-28 19:43:05
 */
public class LeetCode_2673_1_使二叉树所有路径值相等的最小代价 {
    public int minIncrements(int n, int[] cost) {
        int ans = 0;
        for (int i = n - 2; i > 0; i -= 2) {
            ans += Math.abs(cost[i] - cost[i + 1]);
            cost[i / 2] += Math.max(cost[i], cost[i + 1]);
        }
        return ans;
    }
}
