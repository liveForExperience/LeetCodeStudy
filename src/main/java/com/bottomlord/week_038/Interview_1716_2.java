package com.bottomlord.week_038;

/**
 * @author ChenYue
 * @date 2020/3/24 8:10
 */
public class Interview_1716_2 {
    public int massage(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int dp0 = 0, dp1 = nums[0], len = nums.length;
        for (int i = 1; i < len; i++) {
            int tDp0 = Math.max(dp1, dp0);
            int tDp1 = dp0 + nums[i];

            dp0 = tDp0;
            dp1 = tDp1;
        }

        return Math.max(dp0, dp1);
    }
}