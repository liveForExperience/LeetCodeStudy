package com.bottomlord.week_190;

/**
 * @author chen yue
 * @date 2023-02-27 08:27:10
 */
public class LeetCode_1144_1_递减元素使数组呈锯齿状 {
    public int movesToMakeZigzag(int[] nums) {
        int n = nums.length, odd = 0, even = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = i == 0 ? Integer.MAX_VALUE : nums[i - 1],
                right = i == n - 1 ? Integer.MAX_VALUE : nums[i + 1],
                cur = nums[i], diff = Math.max(cur - Math.min(left, right) + 1, 0);
            if (i % 2 == 0) {
                even += diff;
            } else {
                odd += diff;
            }
        }

        return Math.min(even, odd);
    }
}
