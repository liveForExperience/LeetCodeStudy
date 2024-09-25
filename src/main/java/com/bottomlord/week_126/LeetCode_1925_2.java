package com.bottomlord.week_126;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-12-06 09:17:23
 */
public class LeetCode_1925_2 {
    public int countTriples(int n) {
        int count = 0;
        Set<Double> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(i * 1.0);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                double a = Math.pow(i, 2), b = Math.pow(j, 2);
                if (set.contains(Math.sqrt(a + b))) {
                    count += 2;
                }
            }
        }

        return count;
    }
}