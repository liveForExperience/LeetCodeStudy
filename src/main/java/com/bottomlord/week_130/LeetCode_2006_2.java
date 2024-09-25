package com.bottomlord.week_130;

/**
 * @author chen yue
 * @date 2022-01-06 08:54:28
 */
public class LeetCode_2006_2 {
    public int countKDifference(int[] nums, int k) {
        int[] bucket = new int[201];
        int count = 0;
        for (int num : nums) {
            bucket[num]++;
        }

        for (int num : nums) {
            count += bucket[num + k];
        }

        return count;
    }
}