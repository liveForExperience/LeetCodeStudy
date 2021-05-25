package com.bottomlord.week_098;

public class LeetCode_LCP28_1_采购方案 {
    public int purchasePlans(int[] nums, int target) {
        int len = nums.length, count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] + nums[j] <= target) {
                    count = (count + 1) % 1000000007;
                }
            }
        }
        return count;
    }
}
