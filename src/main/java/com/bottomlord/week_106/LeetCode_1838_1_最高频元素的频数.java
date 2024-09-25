package com.bottomlord.week_106;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/7/19 8:20
 */
public class LeetCode_1838_1_最高频元素的频数 {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0, n = nums.length, ans = 1;
        long total = 0;
        for (int r = 1; r < n; r++) {
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                total -= (nums[r] - nums[l]);
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
