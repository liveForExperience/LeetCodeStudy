package com.bottomlord.week_4;

import java.util.Arrays;

/**
 * @author LiveForExperience
 * @date 2019/8/4 14:46
 */
public class LeetCode_1029_两地调度 {
    public int twoCitySchedCost(int[][] costs) {
        int ans = 0;
        int[] arr = new int[costs.length];
        for (int i = 0; i < costs.length; i++) {
            int[] cost = costs[i];
            ans += cost[0];
            arr[i] = cost[1] - cost[0];
        }

        Arrays.sort(arr);
        for (int i = 0; i < arr.length / 2; i++) {
            ans += arr[i];
        }

        return ans;
    }
}
