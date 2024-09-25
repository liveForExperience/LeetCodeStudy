package com.bottomlord.week_017;

import java.util.Arrays;

public class LeetCode_462_2 {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int start = 0, end = nums.length - 1, ans = 0;
        while (start < end) {
            ans += nums[start++] + nums[end--];
        }
        return ans;
    }
}