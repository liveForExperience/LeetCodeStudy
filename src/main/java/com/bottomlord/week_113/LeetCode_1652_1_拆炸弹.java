package com.bottomlord.week_113;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-09-10 08:44:22
 */
public class LeetCode_1652_1_拆炸弹 {
    public int[] decrypt(int[] code, int k) {
        int len = code.length;
        int[] ans = new int[len];
        if (k == 0) {
            return ans;
        }

        boolean positive = k > 0;
        for (int i = 0; i < len; i++) {
            if (positive) {
                for (int j = 1; j <= k; j++) {
                    ans[i] += code[(i + j) % len];
                }
            } else {
                for (int j = -1; j >= k; j--) {
                    ans[i] += code[(i + j + len) % len];
                }
            }
        }

        return ans;
    }
}
