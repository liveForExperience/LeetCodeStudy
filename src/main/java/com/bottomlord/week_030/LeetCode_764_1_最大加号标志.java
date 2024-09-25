package com.bottomlord.week_030;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ThinkPad
 * @date 2020/1/29 10:51
 */
public class LeetCode_764_1_最大加号标志 {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        Set<Integer> set = new HashSet<>();
        for (int[] mine : mines) {
            set.add(mine[0] * N + mine[1]);
        }

        int ans = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int k = 0;
                while (k <= r && k + r < N && k <= c && k + c < N &&
                        !set.contains((r - k) * N + c) &&
                        !set.contains((r + k) * N + c) &&
                        !set.contains(r * N + c - k) &&
                        !set.contains(r * N + c + k)) {
                    k++;
                }
                ans = Math.max(ans, k);
            }
        }
        return ans;
    }
}
