package com.bottomlord.week_017;

import java.util.Arrays;

public class LeetCode_462_1_最少移动次数使数组元素相等II {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length, mid = len % 2 == 0 ? (nums[len / 2] + nums[len / 2 - 1]) / 2 : nums[len / 2], ans = 0;
        for (int num : nums) {
            ans += Math.abs(num - mid);
        }
        return ans;
    }
}
