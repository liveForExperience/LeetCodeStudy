package com.bottomlord.week_104;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2021/7/7 8:16
 */
public class LeetCode_1711_1_大餐计数 {
    public int countPairs(int[] deliciousness) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int d : deliciousness) {
            map.put(d, map.getOrDefault(d, 0) + 1);
        }

        int[] sum = new int[22];
        sum[0] = 1;
        for (int i = 1; i <= 21; i++) {
            sum[i] = sum[i - 1] * 2;
        }

        Set<Integer> memo = new HashSet<>();
        long count = 0;
        for (Integer d : map.keySet()) {
            for (int num : sum) {
                if (memo.contains(num - d)) {
                    continue;
                }

                if (map.containsKey(num - d)) {
                    long c = map.get(num - d);

                    if (num - d == d) {
                        count += (c * (c - 1) / 2) % 1000000007;
                    } else {
                        count += (c * map.get(d)) % 1000000007;
                    }

                    memo.add(d);
                }
            }
        }

        return (int)count;
    }
}
