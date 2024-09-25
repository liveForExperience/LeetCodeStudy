package com.bottomlord.week_130;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-01-03 19:30:34
 */
public class LeetCode_1984_1_学生分数的最小差值 {
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE, n = nums.length;
        for (int i = 0; i < n - k + 1; i++) {
            ans = Math.min(ans, nums[i + k - 1] - nums[i]);
        }
        return ans;
    }
}
