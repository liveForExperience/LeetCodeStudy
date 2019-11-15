package com.bottomlord.week_019;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_343_1_整数拆分 {
    public int integerBreak(int n) {
        return rescue(n, new HashMap<>());
    }

    private int rescue(int n, Map<Integer, Integer> memo) {
        if (n <= 1) {
            return n;
        }

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int max = 0;
        for (int i  = 1; i < n; i++) {
            max = Math.max(max, Math.max(i * rescue(n - i, memo), i * (n - i)));
        }

        memo.put(n, max);
        return max;
    }
}