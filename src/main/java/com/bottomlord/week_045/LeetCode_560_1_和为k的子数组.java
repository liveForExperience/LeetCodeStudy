package com.bottomlord.week_045;

/**
 * @author ChenYue
 * @date 2020/5/15 8:10
 */
public class LeetCode_560_1_和为k的子数组 {
    public int subarraySum(int[] nums, int k) {
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    ans += 1;
                }
            }
        }

        return ans;
    }
}