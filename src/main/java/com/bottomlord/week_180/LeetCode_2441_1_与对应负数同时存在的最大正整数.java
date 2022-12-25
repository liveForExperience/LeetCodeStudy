package com.bottomlord.week_180;

/**
 * @author chen yue
 * @date 2022-12-25 19:48:30
 */
public class LeetCode_2441_1_与对应负数同时存在的最大正整数 {
    public int findMaxK(int[] nums) {
        boolean[] bucket = new boolean[2001];
        for (int num : nums) {
            bucket[num + 1000] = true;
        }

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] && bucket[-(i - 1000) + 1000]) {
                return -(i - 1000);
            }
        }

        return -1;
    }
}
