package com.bottomlord.week_121;

/**
 * @author chen yue
 * @date 2021-11-06 14:26:45
 */
public class LeetCode_268_2 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int[] bucket = new int[n + 1];
        for (int j : nums) {
            bucket[j]++;
        }

        for (int num : bucket) {
            if (num == 0) {
                return num;
            }
        }

        return -1;
    }
}
