package com.bottomlord.week_214;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-08-15 15:32:44
 */
public class LeetCode_1681_1_最小不兼容性 {
    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length, m = n / k;
        int[] g = new int[1 << n];
        Arrays.fill(g, -1);
        for (int i = 1; i < 1 << n; i++) {
            if (Integer.bitCount(i) != m) {
                continue;
            }

            boolean[] memo = new boolean[n + 1];
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    if (memo[nums[j]]) {
                        continue;
                    }

                    memo[nums[j]] = true;
                    min = Math.min(min, nums[j]);
                    max = Math.max(max, nums[j]);
                }
            }

            if (count(memo) == m) {
                g[i] = max - min;
            }
        }

        int[] f = new int[1 << n];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for (int i = 0; i < 1 << n; i++) {
            if (f[i] == Integer.MAX_VALUE) {
                continue;
            }

            boolean[] memo = new boolean[n + 1];
            int mask = 0;
            for (int j = 0; j < n; j++) {
                if ((1 << j & i) == 0 && !memo[nums[j]]) {
                    memo[nums[j]] = true;
                    mask |= 1 << j;
                }
            }

            if (count(memo) < m) {
                continue;
            }

            for (int j = mask; j > 0; j = (j - 1) & mask) {
                if (g[j] == -1) {
                    continue;
                }

                f[i | j] = Math.min(f[i | j], f[i] + g[j]);
            }
        }

        return f[(1 << n) - 1] == Integer.MAX_VALUE ? -1 : f[(1 << n) - 1];
    }

    private int count(boolean[] arr) {
        int c = 0;
        for (boolean b : arr) {
            if (b) {
                c++;
            }
        }
        return c;
    }
}
