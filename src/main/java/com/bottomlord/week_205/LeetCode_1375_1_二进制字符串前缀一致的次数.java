package com.bottomlord.week_205;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * @author chen yue
 * @date 2023-06-14 19:43:48
 */
public class LeetCode_1375_1_二进制字符串前缀一致的次数 {
    public int numTimesAllBlue(int[] flips) {
        int max = -1, count = 0;
        for (int i = 0; i < flips.length; i++) {
            max = Math.max(max, flips[i]);

            if (max == i + 1) {
                count++;
            }
        }

        return count;
    }
}
