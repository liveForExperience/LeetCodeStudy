package com.bottomlord.week_018;

import java.util.HashSet;
import java.util.Set;

public class LeetCode_983_1_最低票价 {
    public int mincostTickets(int[] days, int[] costs) {
        Set<Integer> set = new HashSet<>();
        for (int day : days) {
            set.add(day);
        }
        return dp(0, costs, set, new Integer[366]);
    }

    private int dp(int day, int[] costs, Set<Integer> set, Integer[] memo) {
        if (day > 365) {
            return 0;
        }

        if (memo[day] != null) {
            return memo[day];
        }

        int ans;
        if (set.contains(day)) {
            ans = Math.min(dp(day + 1, costs, set, memo) + costs[0], dp(day + 7, costs, set, memo) + costs[1]);
            ans = Math.min(ans, dp(day + 30, costs, set, memo) + costs[2]);
        } else {
            ans = dp(day + 1, costs, set, memo);
        }

        memo[day] = ans;
        return ans;
    }
}
