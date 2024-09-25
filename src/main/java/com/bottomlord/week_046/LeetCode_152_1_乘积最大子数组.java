package com.bottomlord.week_046;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/5/18 8:17
 */
public class LeetCode_152_1_乘积最大子数组 {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        int[] dpMax = new int[len], dpMin = new int[len];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];

        for (int i = 1; i < len; i++) {
            int num = nums[i];
            dpMax[i] = Math.max(dpMax[i - 1] * num, Math.max(dpMin[i - 1] * num, num));
            dpMin[i] = Math.min(dpMax[i - 1] * num, Math.min(dpMin[i - 1] * num, num));
        }

        return Arrays.stream(dpMax).max().getAsInt();
    }
}
