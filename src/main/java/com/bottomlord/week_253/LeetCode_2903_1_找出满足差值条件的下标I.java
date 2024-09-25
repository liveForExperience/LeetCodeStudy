package com.bottomlord.week_253;

/**
 * @author chen yue
 * @date 2024-05-25 14:02:49
 */
public class LeetCode_2903_1_找出满足差值条件的下标I {
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + indexDifference; j < n; j++) {
                if (Math.abs(nums[i] - nums[j]) >= valueDifference) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{-1, -1};
    }
}
