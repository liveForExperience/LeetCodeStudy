package com.bottomlord.week_109;

/**
 * @author ChenYue
 * @date 2021/8/10 8:19
 */
public class LeetCode_413_1_等差数列划分 {
    public int numberOfArithmeticSlices(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int diff = nums[i + 1] - nums[i],
                pre = nums[i + 1];
            for (int j = i + 2; j < nums.length; j++) {
                if (nums[j] - pre == diff) {
                    count++;
                    pre = nums[j];
                } else {
                    break;
                }
            }
        }

        return count;
    }
}
