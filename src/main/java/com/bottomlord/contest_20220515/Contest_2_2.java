package com.bottomlord.contest_20220515;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-05-15 10:48:13
 */
public class Contest_2_2 {
    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int max = 0, n = special.length;
        for (int i = 0; i < n; i++) {
            if (special[i] > bottom) {
                max = Math.max(special[i] - bottom, max);
            }

            bottom = special[i] + 1;
        }

        if (top > special[n - 1]) {
            max = Math.max(top - special[n - 1], max);
        }

        return max;
    }
}