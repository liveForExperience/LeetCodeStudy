package com.bottomlord.week_126;

/**
 * @author chen yue
 * @date 2021-12-06 09:01:21
 */
public class LeetCode_1920_1_基于排列构建数组 {
    public int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }
        return ans;
    }
}
