package com.bottomlord.week_123;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2021-11-19 08:39:28
 */
public class LeetCode_397_1_整数替换 {
    public int integerReplacement(int n) {
        return dfs(n, new HashMap<>());
    }

    private int dfs(long n, Map<Long, Integer> memo) {
        if (n == 1) {
            return 0;
        }

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int ans;
        if (n % 2 == 0) {
            ans = dfs(n / 2, memo);
        } else {
            int plusOne = dfs(n + 1, memo),
                subtractOne = dfs(n - 1, memo);

            ans = Math.min(plusOne, subtractOne);
        }

        memo.put(n, ans + 1);
        return ans + 1;
    }
}
