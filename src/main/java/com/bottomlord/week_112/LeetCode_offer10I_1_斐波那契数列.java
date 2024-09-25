package com.bottomlord.week_112;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2021-09-04 14:49:26
 */
public class LeetCode_offer10I_1_斐波那契数列 {
    public int fib(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        memo.put(0, 0);
        memo.put(1, 1);
        memo.put(2, 1);
        return doFib(n, memo);
    }

    private int doFib(int n, Map<Integer, Integer> memo) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        memo.put(n - 1, doFib(n - 1, memo));
        memo.put(n - 2, doFib(n - 2, memo));
        return (memo.get(n - 1) + memo.get(n - 2)) % 1000000007;
    }
}
