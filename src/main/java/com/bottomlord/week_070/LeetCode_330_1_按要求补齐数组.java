package com.bottomlord.week_070;

/**
 * @author ChenYue
 * @date 2020/11/9 8:50
 */
public class LeetCode_330_1_按要求补齐数组 {
    public int minPatches(int[] nums, int n) {
        int len = nums.length, index = 0, count = 0;
        long miss = 1;
        while (miss <= n) {
            if (index < len && nums[index] <= miss) {
                miss += nums[index++];
            } else {
                miss += miss;
                count++;
            }
        }
        return count;
    }
}
