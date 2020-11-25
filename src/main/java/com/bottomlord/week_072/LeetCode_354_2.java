package com.bottomlord.week_072;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/11/25 10:08
 */
public class LeetCode_354_2 {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, (x, y) -> {
            if (x[0] == y[0]) {
                return y[1] - x[1];
            }

            return x[0] - y[0];
        });

        int piles = 0;
        int[] top = new int[envelopes.length];
        for (int[] envelope : envelopes) {
            int num = envelope[1];
            int left = 0, right = piles;
            while (left < right) {
                int mid = left + (right - left) / 2;

                if (top[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            if (left == piles) {
                piles++;
            }

            top[left] = num;
        }

        return piles;
    }
}
