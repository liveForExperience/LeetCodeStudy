package com.bottomlord.week_185;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-01-23 12:24:30
 */
public class LeetCode_1815_1_得到新鲜甜甜圈的最多组数 {

    private int width = 5, rangeMask = (1 << width) - 1;

    public int maxHappyGroups(int batchSize, int[] groups) {
        int[] counts = new int[batchSize];
        for (int group : groups) {
            counts[group % batchSize]++;
        }

        long status = 0;
        for (int i = batchSize - 1; i >= 1; i--) {
            status = (status << width) | counts[i];
        }

        return dfs(status, new HashMap<>(), batchSize) + counts[0];
    }

    private int dfs(long status, Map<Long, Integer> map, int batchSize) {
        if (status == 0) {
            return 0;
        }

        if (map.containsKey(status)) {
            return map.get(status);
        }

        long cur = 0;
        for (int i = 1; i < batchSize; i++) {
            cur += i * getAmount(status, i);
        }

        int best = 0;
        for (int i = 1; i < batchSize; i++) {
            long amount = getAmount(status, i);
            if (amount == 0) {
                continue;
            }

            int result = dfs(status - (1L << (i - 1) * width), map, batchSize);
            if ((cur - i) % batchSize == 0) {
                result++;
            }

            best = Math.max(result, best);
        }

        map.put(status, best);
        return best;
    }

    private long getAmount(long status, int modIndex) {
        return (status >> (modIndex - 1) * width) & rangeMask;
    }
}
