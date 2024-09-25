package com.bottomlord.week_007;

public class LeetCode_746_1_使用最小花费爬楼梯 {
    public int minCostClimbingStairs(int[] cost) {
        return reverse(cost, -1, 0);
    }

    private int reverse(int[] cost, int index, int sum) {
        if (index >= cost.length) {
            return sum;
        }

        sum += cost[index];

        return Math.min(reverse(cost, index + 1, sum), reverse(cost, index + 2, sum));
    }
}
