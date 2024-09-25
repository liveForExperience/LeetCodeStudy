package com.bottomlord.week_192;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-03-14 23:19:29
 */
public class LeetCode_910_1_最小差值II {
    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = nums[n - 1] - k - nums[0] - k;
        for (int i = 1; i < nums.length; i++) {
            int min = Math.min(nums[0] + k, nums[i] - k),
                max = Math.max(nums[n - 1] - k, nums[i - 1] + k);
            ans = Math.min(ans, max - min);
        }
        return ans;
    }
}
