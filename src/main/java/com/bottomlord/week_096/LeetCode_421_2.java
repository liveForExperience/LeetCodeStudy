package com.bottomlord.week_096;

public class LeetCode_421_2 {
    public int findMaximumXOR(int[] nums) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                ans = Math.max(ans, nums[i] ^ nums[j]);
            }
        }
        return ans;
    }
}
