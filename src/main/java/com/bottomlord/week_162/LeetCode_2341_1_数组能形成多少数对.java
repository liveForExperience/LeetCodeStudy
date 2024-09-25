package com.bottomlord.week_162;

/**
 * @author chen yue
 * @date 2022-08-21 16:56:50
 */
public class LeetCode_2341_1_数组能形成多少数对 {
    public int[] numberOfPairs(int[] nums) {
        int[] bucket = new int[101];
        for (int num : nums) {
            bucket[num]++;
        }

        int count = 0, left = 0;
        for (int num : bucket) {
            count += num / 2;
            left += num % 2 == 1 ? 1 : 0;
        }

        return new int[]{count, left};
    }
}
