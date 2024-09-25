package com.bottomlord.week_113;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author chen yue
 * @date 2021-09-10 08:55:23
 */
public class LeetCode_1652_2 {
    public int[] decrypt(int[] code, int k) {
        int len = code.length;
        int[] ans = new int[len];

        if (k == 0) {
            return ans;
        }

        int[] sums = new int[len];
        for (int i = 0; i < len; i++) {
            int pre = i == 0 ? 0 : sums[i - 1];
            sums[i] = pre + code[i];
        }

        boolean positive = k > 0;
        for (int i = 0; i < len; i++) {
            if (positive) {
                if (k <= len - i - 1) {
                    ans[i] = sums[i + k] - sums[i];
                } else {
                    ans[i] += sums[len - 1] - sums[i] + sums[k - len + i];
                }
            } else {
                int dis = Math.abs(k);
                if (i + 1 > dis) {
                    ans[i] = sums[i - 1] - ((i - dis > 0) ? sums[i - dis - 1] : 0);
                } else if (i == 0) {
                    ans[i] = sums[len - 1] - sums[len - dis - 1];
                } else {
                    ans[i] = sums[i - 1] + sums[len - 1] - sums[len - dis + i - 1];
                }
            }
        }

        return ans;
    }
}