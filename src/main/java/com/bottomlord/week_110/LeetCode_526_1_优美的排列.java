package com.bottomlord.week_110;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-08-16 08:19:58
 */
public class LeetCode_526_1_优美的排列 {
    public int countArrangement(int n) {
        List<Integer>[] dict = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            dict[i] = new ArrayList<>();
            for (int j = 1; j <= n; j++) {
                if (i % j == 0 || j % i == 0) {
                    dict[i].add(j);
                }
            }
        }

        return backTrack(1, n, new boolean[n + 1], dict);
    }

    private int backTrack(int index, int n, boolean[] memo, List<Integer>[] dict) {
        if (index > n) {
            return 1;
        }

        List<Integer> list = dict[index];
        int count = 0;
        for (int num : list) {
            if (!memo[num]) {
                memo[num] = true;
                count += backTrack(index + 1, n, memo, dict);
                memo[num] = false;
            }
        }

        return count;
    }
}
