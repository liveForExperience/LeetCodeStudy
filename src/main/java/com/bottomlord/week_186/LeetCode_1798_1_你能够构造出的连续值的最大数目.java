package com.bottomlord.week_186;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-02-04 09:47:42
 */
public class LeetCode_1798_1_你能够构造出的连续值的最大数目 {
    public int getMaximumConsecutive(int[] coins) {
        Arrays.sort(coins);
        int x = 0;
        for (int coin : coins) {
            if (coin <= x + 1) {
                x = coin + x;
            }
        }

        return x + 1;
    }
}
