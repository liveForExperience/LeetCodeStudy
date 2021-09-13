package com.bottomlord.week_114;

/**
 * @author chen yue
 * @date 2021-09-13 20:36:30
 */
public class LeetCode_1668_2 {
    public int maxRepeating(String sequence, String word) {
        int max = 0, len = sequence.length(), wlen = word.length();
        char[] wcs = word.toCharArray(), scs = sequence.toCharArray();
        for (int i = 0; i < len; i++) {
            if (wcs[0] == scs[i]) {
                int w = 0, k = i;
                while (k < len && wcs[w % wlen] == scs[k]) {
                    k++;
                    w++;
                }

                max = Math.max(max, w / wlen);
            }
        }

        return max;
    }
}
