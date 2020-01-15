package com.bottomlord.week_028;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/1/15 21:14
 */
public class LeetCode_60_1_第k个排列 {
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i<= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        boolean[] memo = new boolean[n + 1];
        List<Integer> list = new ArrayList<>();
        dfs(0, list, n, k, factorial, memo);

        StringBuilder sb = new StringBuilder();
        for (Integer num : list) {
            sb.append(num);
        }
        return sb.toString();
    }

    private void dfs(int index, List<Integer> list, int n, int k, int[] factorial, boolean[] memo) {
        if (index == n) {
            return;
        }

        int count = factorial[n - 1 - index];
        for (int i = 1; i <= n; i++) {
            if (memo[i]) {
                continue;
            }

            if (k > count) {
                k -= count;
                continue;
            }

            memo[i] = true;
            list.add(i);
            dfs(index + 1, list, n, k, factorial, memo);
        }
    }

}
