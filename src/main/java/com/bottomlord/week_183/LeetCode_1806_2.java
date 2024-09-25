package com.bottomlord.week_183;

/**
 * @author chen yue
 * @date 2023-01-09 06:22:45
 */
public class LeetCode_1806_2 {
    public int reinitializePermutation(int n) {
        int i = 1, path = 0;
        while (true) {
            i = i % 2 == 0 ? i / 2 : n / 2 + (i - 1) / 2;
            path++;

            if (i == 1) {
                return path;
            }
        }
    }
}
