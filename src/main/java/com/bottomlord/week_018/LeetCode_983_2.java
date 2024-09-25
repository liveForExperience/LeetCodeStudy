package com.bottomlord.week_018;

public class LeetCode_983_2 {
    public int mincostTickets(int[] days, int[] costs) {
        int[] durations = new int[]{1, 7, 30};
        return dp(0, days, costs, durations, new Integer[days.length]);
    }

    private int dp(int index, int[] days, int[] costs, int[] durations, Integer[] memo) {
        if (index >= days.length) {
            return 0;
        }

        if (memo[index] != null) {
            return memo[index];
        }

        int tmp = index, ans = Integer.MAX_VALUE;
        for (int k = 0; k < 3; k++) {
            while (tmp < days.length && days[tmp] < days[index] + durations[k]) {
                tmp++;
            }

            ans = Math.min(ans, dp(tmp, days, costs, durations, memo) + costs[k]);
        }

        memo[index] = ans;
        return ans;
    }
}