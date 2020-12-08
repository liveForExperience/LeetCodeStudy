package com.bottomlord.week_032;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/12/8 8:59
 */
public class Interview_10I_3 {
    public int fib(int n) {
        return f(n, new HashMap<>());
    }

    private int f(int n, Map<Integer, Integer> memo) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        if (n <= 1) {
            memo.put(n, n);
            return n;
        }

        int ans = (f(n - 1, memo) + f(n - 2, memo)) % 1000000007;
        memo.put(n, ans);
        return ans;
    }
}
