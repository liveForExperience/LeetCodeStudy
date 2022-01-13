package com.bottomlord.week_131;

/**
 * @author chen yue
 * @date 2022-01-13 20:40:21
 */
public class LeetCode_2057_1_值相等的最小索引 {
    public int smallestEqual(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i % 10 == nums[i]) {
                return i;
            }
        }

        return -1;
    }
}
