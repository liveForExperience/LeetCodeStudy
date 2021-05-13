package com.bottomlord.week_096;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/5/13 8:20
 */
public class LeetCode_1269_2 {
    private int mod = 1000000007;
    public int numWays(int steps, int arrLen) {
        return dfs(steps, arrLen, 0, 0, new HashMap<>());
    }

    private int dfs(int steps, int arrLen, int index, int step, Map<String, Integer> memo) {
        if (index < 0 || index >= arrLen) {
            return 0;
        }

        String key = index + ":" + step;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (step == steps) {
            return index == 0 ? 1 : 0;
        }

        if (index > steps - step) {
            return 0;
        }

        int count = 0;
        for (int i = -1; i < 2; i++) {
            count += dfs(steps, arrLen, index + i, step + 1, memo);
            count %= mod;
        }

        memo.put(key, count);
        return count % mod;
    }
}
