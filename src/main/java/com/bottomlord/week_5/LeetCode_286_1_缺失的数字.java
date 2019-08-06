package com.bottomlord.week_5;

/**
 * @author LiveForExperience
 * @date 2019/8/6 12:57
 */
public class LeetCode_286_1_缺失的数字 {
    public int missingNumber(int[] nums) {
        int max = 0, min = nums.length, sum = 0;
        for (int num : nums) {
            min = Math.min(num, min);
            max = Math.max(num, max);
            sum += num;
        }

        if (min != 0) {
            return 0;
        }

        int orign = max * (max + 1) / 2;
        return orign != sum ? orign - sum : max + 1;
    }
}
