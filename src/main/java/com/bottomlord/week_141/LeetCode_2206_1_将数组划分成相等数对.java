package com.bottomlord.week_141;

/**
 * @author chen yue
 * @date 2022-03-26 22:49:59
 */
public class LeetCode_2206_1_将数组划分成相等数对 {
    public boolean divideArray(int[] nums) {
        int[] bucket = new int[501];
        for (int num : nums) {
            bucket[num]++;
        }

        for (int i = 1; i < bucket.length; i++) {
            if (bucket[i] % 2 != 0) {
                return false;
            }
        }

        return true;
    }
}
