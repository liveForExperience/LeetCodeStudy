package com.bottomlord.week_139;

/**
 * @author chen yue
 * @date 2022-03-09 10:51:37
 */
public class LeetCode_798_2 {
    public int bestRotation(int[] nums) {
        int len = nums.length, val = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= i) {
                val++;
            }
        }

        int[] steps = new int[len];
        for (int i = 0; i < steps.length; i++) {
            if (nums[i] <= i) {
                steps[i - nums[i]]++;
            } else {
                steps[len - (nums[i] - i)]++;
            }
        }

        int max = val, ans = 0;
        for (int i = 1; i < nums.length; i++) {
            val = val - steps[i - 1] + 1;
            if (val > max) {
                max = val;
                ans = i;
            }
        }

        return ans;
    }
}
