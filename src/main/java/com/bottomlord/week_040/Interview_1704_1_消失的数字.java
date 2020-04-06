package com.bottomlord.week_040;

/**
 * @author ChenYue
 * @date 2020/4/6 22:12
 */
public class Interview_1704_1_消失的数字 {
    public int missingNumber(int[] nums) {
        int origin = nums.length * (nums.length + 1) / 2;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        return origin - sum;
    }
}
