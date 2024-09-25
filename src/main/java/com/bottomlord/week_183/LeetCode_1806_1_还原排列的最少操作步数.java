package com.bottomlord.week_183;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-01-09 06:16:33
 */
public class LeetCode_1806_1_还原排列的最少操作步数 {
    public int reinitializePermutation(int n) {
        int[] perm = new int[n], arr = new int[n];
        for (int i = 0; i < n; i++) {
            perm[i] = i;
            arr[i] = i;
        }

        int ans = 0;
        while (true) {
            int[] cur = new int[n];

            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    cur[i] = arr[i / 2];
                } else {
                    cur[i] = arr[n / 2 + (i - 1) / 2];
                }
            }

            ans++;
            if (Arrays.equals(cur, perm)) {
                break;
            }

            arr = cur;
        }

        return ans;
    }
}
