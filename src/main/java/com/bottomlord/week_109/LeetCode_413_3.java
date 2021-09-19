package com.bottomlord.week_109;

/**
 * @author ChenYue
 * @date 2021/8/10 9:04
 */
public class LeetCode_413_3 {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }

        int diff = nums[1] - nums[0], total = 0, ans = 0;
        for (int i = 2; i < n; i++) {
            if (nums[i] - nums[i - 1] == diff) {
                total++;
            } else {
                diff = nums[i] - nums[i - 1];
                total = 0;
            }

            ans += total;
        }

        return ans;
    }
}
