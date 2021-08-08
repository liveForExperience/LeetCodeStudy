package com.bottomlord.week_108;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2021-08-08 14:18:17
 */
public class LeetCode_1137_3 {
    public int tribonacci(int n) {
        if (n <= 1) {
            return n;
        }

        if (n == 2) {
            return 1;
        }

        Map<Integer, Integer> memo = new HashMap<>();
        memo.put(0, 0);
        memo.put(1, 1);
        memo.put(2, 1);
        return doTri(n - 1, memo) + doTri(n - 2, memo) + doTri(n - 3, memo);
    }

    private int doTri(int n, Map<Integer, Integer> memo) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int num = doTri(n - 1, memo) + doTri(n - 2, memo) + doTri(n - 3, memo);
        memo.put(n, num);

        return num;
    }
}
