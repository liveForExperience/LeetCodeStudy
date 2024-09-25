package com.bottomlord.week_020;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_932_1_漂亮数组 {
    public int[] beautifulArray(int N) {
        Map<Integer, int[]> memo = new HashMap<>();
        return recurse(N, memo);
    }

    private int[] recurse(int n, Map<Integer, int[]> memo) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int[] ans = new int[n];

        if (n == 1) {
            ans[0] = 1;
        } else {
            int index = 0;
            for (int i : recurse((n + 1) / 2, memo)) {
                ans[index++] = 2 * i - 1;
            }

            for (int i : recurse(n / 2, memo)) {
                ans[index++] = 2 * i;
            }
        }

        memo.put(n, ans);
        return ans;
    }
}
