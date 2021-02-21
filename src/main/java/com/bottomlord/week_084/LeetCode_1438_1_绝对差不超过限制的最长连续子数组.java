package com.bottomlord.week_084;

/**
 * @author ChenYue
 * @date 2021/2/21 14:56
 */
public class LeetCode_1438_1_绝对差不超过限制的最长连续子数组 {
    public int longestSubarray(int[] nums, int limit) {
        int len = nums.length, ans = 0;
        for (int i = 0; i < len; i++) {
            int max = nums[i], min = nums[i], j = i;
            while (j < len && Math.abs(nums[j] - max) <= limit && Math.abs(nums[j] - min) <= limit) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                j++;
            }

            ans = Math.max(ans, j - i);
        }

        return ans;
    }
}
