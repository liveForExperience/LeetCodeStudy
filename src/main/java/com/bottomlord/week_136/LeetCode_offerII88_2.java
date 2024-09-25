package com.bottomlord.week_136;

/**
 * @author chen yue
 * @date 2022-02-17 09:02:27
 */
public class LeetCode_offerII88_2 {
    public int minCostClimbingStairs(int[] cost) {
        int one = cost[0], two = cost[1], len = cost.length, ans;
        for (int i = 2; i < len; i++) {
            ans = Math.min(one, two) + cost[i];
            one = two;
            two = ans;
        }

        return Math.min(one, two);
    }
}
