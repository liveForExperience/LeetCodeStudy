package com.bottomlord.week_042;

/**
 * @author ChenYue
 * @date 2020/4/23 9:03
 */
public class LeetCode_466_2 {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int len1 = s1.length(), len2 = s2.length();

        if (len1 == 0 || n1 == 0 || len2 == 0 || n2 == 0) {
            return 0;
        }

        char[] cs1 = s1.toCharArray(), cs2 = s2.toCharArray();

        int[] times = new int[n1], next = new int[n1];

        int i2 = 0, loop2 = 0;
        for (int loop1 = 0; loop1 < n1; loop1++) {
            for (int i1 = 0; i1 < len1; i1++) {
                if (cs1[i1] == cs2[i2]) {
                    i2++;
                }

                if (i2 == len2) {
                    loop2++;
                    i2 = 0;
                }
            }

            times[loop1] = loop2;
            next[loop1] = i2;

            if (loop1 > 0 && next[loop1] == i2) {
                int head = times[0];

                int mid = ((n1 - 1) / loop1) * (times[loop1] - times[0]);

                int end = times[(n1 - 1) % loop1] - times[0];

                return (head + mid + end) / n2;
            }
        }

        return times[n1 - 1] / n2;
    }
}
