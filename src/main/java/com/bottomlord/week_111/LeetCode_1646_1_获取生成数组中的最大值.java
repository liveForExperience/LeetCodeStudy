package com.bottomlord.week_111;

/**
 * @author chen yue
 * @date 2021-08-23 08:13:05
 */
public class LeetCode_1646_1_获取生成数组中的最大值 {
    public int getMaximumGenerated(int n) {
        if (n <= 1) {
            return n;
        }

        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;

        int max = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                nums[i] = nums[i / 2];
            } else {
                nums[i] = nums[i / 2] + nums[i / 2 + 1];
            }

            max = Math.max(max, nums[i]);
        }

        return max;
    }
}
