package com.bottomlord.week_7;

public class LeetCode_198_1_打家劫舍 {
    public int rob(int[] nums) {
        int preMax = 0, curMax = 0;
        for (int num : nums) {
            int tmp = curMax;
            curMax = Math.max(preMax + num, curMax);
            preMax = tmp;
        }
        return curMax;
    }
}
