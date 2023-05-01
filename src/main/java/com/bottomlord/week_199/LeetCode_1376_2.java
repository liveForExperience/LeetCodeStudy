package com.bottomlord.week_199;


import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-05-01 10:21:18
 */
public class LeetCode_1376_2 {
    public int numOfMinutes(int n, int headID, int[] managers, int[] informTimes) {
        int[] costs = new int[n];
        Arrays.fill(costs, -1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(i, costs, informTimes, managers));
        }
        return ans;
    }

    private int dfs(int cur, int[] costs, int[] informTimes, int[] managers) {
        int manager = managers[cur];

        if (manager == -1) {
            costs[cur] = informTimes[cur];
        }

        if (costs[manager] == -1) {
            costs[manager] = dfs(manager, costs, informTimes, managers);
        }

        return costs[cur] = costs[manager] + informTimes[cur];
    }
}
