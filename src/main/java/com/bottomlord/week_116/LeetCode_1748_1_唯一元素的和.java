package com.bottomlord.week_116;

/**
 * @author chen yue
 * @date 2021-10-03 19:08:26
 */
public class LeetCode_1748_1_唯一元素的和 {
    public int sumOfUnique(int[] nums) {
        int[] bucket = new int[101];
        for (int num : nums) {
            bucket[num]++;
        }

        int sum = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 1) {
                sum += i;
            }
        }

        return sum;
    }
}
