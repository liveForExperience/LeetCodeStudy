package com.bottomlord.week_163;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-08-26 23:31:32
 */
public class LeetCode_1464_1_数组中两元素的最大乘积 {
    public int maxProduct(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length - 1] * nums[nums.length - 2];
    }
}
