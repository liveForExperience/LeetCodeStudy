package com.bottomlord.week_147;

/**
 * @author chen yue
 * @date 2022-05-05 21:11:19
 */
public class LeetCode_713_1_乘积小于K的子数组 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }

        int n = nums.length, l = 0, cur = 1, ans = 0;
        for (int r = 0; r < n; r++) {
            cur *= nums[r];
            while (cur >= k && l <= r) {
                cur /= nums[l++];
            }

            ans += r - l + 1;
        }

        return ans;
    }
}
