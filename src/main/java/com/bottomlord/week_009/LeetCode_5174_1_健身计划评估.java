package com.bottomlord.week_009;

public class LeetCode_5174_1_健身计划评估 {
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int sum = 0, ans = 0;
        for (int i = 0; i < k; i++) {
            sum += calories[i];
        }

        if (sum > upper) {
            ans++;
        } else if (sum < lower) {
            ans--;
        }

        for (int i = 0; i + k < calories.length; i++) {
            sum = sum - calories[i] + calories[i + k];
            if (sum > upper) {
                ans++;
            } else if (sum < lower) {
                ans--;
            }
        }

        return ans;
    }
}
