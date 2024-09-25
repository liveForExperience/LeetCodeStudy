package com.bottomlord.week_126;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-12-06 08:44:19
 */
public class LeetCode_1913_1_两个数对之间的最大乘积差  {
    public int maxProductDifference(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length - 1] * nums[nums.length - 2] - nums[0] * nums[1];
    }
}
