package com.bottomlord.week_042;

/**
 * @author ChenYue
 * @date 2020/4/22 20:19
 */
public class LeetCode_466_1_统计重复个数 {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int len1 = s1.length(), len2 = s2.length();

        char[] cs1 = s1.toCharArray(), cs2 = s2.toCharArray();

        int i2 = 0, loop2 = 0;

        for (int loop1 = 0; loop1 < n1; loop1++) {
            for (int i1 = 0; i1 < len1; i1++) {
                if (cs1[i1] == cs2[i2]) {
                    i2++;

                    if (i2 == len2) {
                        loop2++;
                        i2 = 0;
                    }
                }
            }
        }

        return loop2 / n2;
    }
}
