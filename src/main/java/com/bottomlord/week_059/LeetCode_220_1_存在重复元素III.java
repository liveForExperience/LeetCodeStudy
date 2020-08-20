package com.bottomlord.week_059;

/**
 * @author ChenYue
 * @date 2020/8/20 8:34
 */
public class LeetCode_220_1_存在重复元素III {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = 1; j <= k && i + j < len; j++) {
                if (Math.abs((long)nums[i] - (long)nums[i + j]) <= (long) t) {
                    return true;
                }
            }
        }

        return false;
    }
}
