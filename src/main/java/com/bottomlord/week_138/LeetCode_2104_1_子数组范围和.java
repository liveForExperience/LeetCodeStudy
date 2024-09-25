package com.bottomlord.week_138;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-03-04 10:47:17
 */
public class LeetCode_2104_1_子数组范围和 {
    private long ans = 0;

    public long subArrayRanges(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            dfs(nums, i, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        return ans;
    }

    private void dfs(int[] nums, int index, int max, int min) {
        if (index >= nums.length) {
            return;
        }

        max = Math.max(max, nums[index]);
        min = Math.min(min, nums[index]);
        ans += max - min;

        dfs(nums, index + 1, max, min);
    }
}
