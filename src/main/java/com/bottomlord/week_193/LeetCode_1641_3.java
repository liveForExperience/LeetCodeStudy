package com.bottomlord.week_193;

/**
 * @author chen yue
 * @date 2023-03-29 22:12:37
 */
public class LeetCode_1641_3 {
    public int countVowelStrings(int n) {
        int a = 1, e = 1, i = 1, o = 1, u = 1;
        for (int j = 0; j < n; j++) {
            a = a + e + i + o + u;
            e = e + i + o + u;
            i = i + o + u;
            o = o + u;
            u = u;
        }

        return a + e + i + o + u;
    }
}
