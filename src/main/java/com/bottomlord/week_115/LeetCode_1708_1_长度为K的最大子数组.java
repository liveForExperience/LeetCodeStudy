package com.bottomlord.week_115;

/**
 * @author chen yue
 * @date 2021-09-24 09:00:19
 */
public class LeetCode_1708_1_长度为K的最大子数组 {
    public int[] largestSubarray(int[] nums, int k) {
        int n = nums.length, start = 0;
        for (int i = 1; i <= n - k; i++) {
            for (int j = 0; j < k; j++) {
                if (nums[start + j] > nums[i + j]) {
                    break;
                } else if (nums[start + j] < nums[i + j]) {
                    start = i;
                    break;
                }
            }
        }

        int[] ans = new int[k];
        System.arraycopy(nums, start, ans, 0, k);
        return ans;
    }
}
