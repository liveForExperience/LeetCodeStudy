package com.bottomlord.week_032;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ThinkPad
 * @date 2020/2/16 11:57
 */
public class Interview_10II_2 {
    public int numWays(int n) {
        return recurse(n, new HashMap<>());
    }

    private int recurse(int n, Map<Integer, Integer> map) {
        if (n == 0) {
            return 1;
        } else if (n <= 2) {
            return n;
        }

        if (map.containsKey(n)) {
            return map.get(n);
        }

        int num = (recurse(n - 1, map) + recurse(n - 2, map)) % 1000000007;
        map.put(n, num);

        return num;
    }
}