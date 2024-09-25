package com.bottomlord.week_073;

/**
 * @author ChenYue
 * @date 2020/11/28 11:25
 */
public class LeetCode_493_1_翻转对 {
    public int reversePairs(int[] nums) {
        int count = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if ((long)nums[i] * 2 < (long)nums[j]) {
                    count++;
                }
            }
        }

        return count;
    }
}
