package com.bottomlord.week_185;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-01-26 16:05:17
 */
public class LeetCode_1663_2 {
    public String getSmallestString(int n, int k) {
        char[] cs = new char[n];
        Arrays.fill(cs, 'a');
        int z = (k - n) / 25;
        int notAorZ = (k - n) % 25;
        cs[n - z - 1] += notAorZ;
        for (int i = n - 1; i >= n - z; i--) {
            cs[i] = 'z';
        }

        return new String(cs);
    }
}
