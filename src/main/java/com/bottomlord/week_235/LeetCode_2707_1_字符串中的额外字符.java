package com.bottomlord.week_235;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2024-01-09 20:37:57
 */
public class LeetCode_2707_1_字符串中的额外字符 {
    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        int[] f = new int[n + 1];
        Set<String> set = new HashSet<>(Arrays.asList(dictionary));

        for (int i = 1; i <= n; i++) {
            f[i] = f[i - 1] + 1;
            for (int j = 0; j < i; j++) {
                if (set.contains(s.substring(j, i))) {
                    f[i] = Math.min(f[i], f[j]);
                }
            }
        }

        return f[n];
    }
}
