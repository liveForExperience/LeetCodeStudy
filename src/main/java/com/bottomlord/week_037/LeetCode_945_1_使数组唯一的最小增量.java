package com.bottomlord.week_037;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/3/22 13:22
 */
public class LeetCode_945_1_使数组唯一的最小增量 {
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int max = 0, sum = 0;
        for (int num : A) {
            if (num < max) {
                sum += max - num + 1;
                max++;
            }
        }
        return sum;
    }
}
