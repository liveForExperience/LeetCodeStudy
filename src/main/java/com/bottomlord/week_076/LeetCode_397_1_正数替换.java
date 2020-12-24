package com.bottomlord.week_076;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/12/23 11:52
 */
public class LeetCode_397_1_正数替换 {
    public int integerReplacement(int n) {
        return recurse(n, new HashMap<>());
    }

    private int recurse(long num, Map<Long, Integer> memo) {
        if (num == 1) {
            return 0;
        }

        if (memo.containsKey(num)) {
            return memo.get(num);
        }

        int count = 0;
        if (num % 2 == 0) {
            count = recurse(num / 2, memo) + 1;
        } else {
            int bCount = recurse(num + 1, memo) + 1,
                sCount = recurse(num - 1, memo) + 1;

            count = Math.min(bCount, sCount);
        }

        memo.put(num, count);
        return count;
    }
}
