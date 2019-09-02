package com.bottomlord.week_005;

import java.util.Arrays;

/**
 * @author LiveForExperience
 * @date 2019/8/5 22:46
 */
public class LeetCode_453_1_最小移动次数使数组元素相等 {
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            ans += nums[i] - nums[i - 1];
        }
        return ans;
    }
}
