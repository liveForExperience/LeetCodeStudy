package com.bottomlord.week_161;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-08-11 08:59:42
 */
public class LeetCode_2335_2 {
    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        int a = amount[0], b = amount[1], c = amount[2];
        if (c >= a + b) {
            return c;
        }

        return (a + b - c + 1) / 2 + c;
    }
}