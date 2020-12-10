package com.bottomlord.week_074;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/12/10 8:44
 */
public class LeetCode_375_3 {
    public int getMoneyAmount(int n) {
        return recurse(1, n, new HashMap<>());
    }

    private int recurse(int start, int end, Map<String, Integer> memo) {
        if (start >= end) {
            return 0;
        }

        if (memo.containsKey(start + " " + end)) {
            return memo.get(start + " " + end);
        }

        int min = Integer.MAX_VALUE;
        for (int i = (start + end) / 2; i <= end; i++) {
            int sum = i + Math.max(recurse(start, i - 1, memo), recurse(i + 1, end, memo));
            min = Math.min(min, sum);
        }

        memo.put(start + " " + end, min);
        return min;
    }
}
