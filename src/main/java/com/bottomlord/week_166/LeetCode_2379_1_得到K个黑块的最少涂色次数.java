package com.bottomlord.week_166;

/**
 * @author chen yue
 * @date 2022-09-14 07:27:54
 */
public class LeetCode_2379_1_得到K个黑块的最少涂色次数 {
    public int minimumRecolors(String blocks, int k) {
        int len = blocks.length();
        int[] sums = new int[len + 1];
        char[] cs = blocks.toCharArray();
        for (int i = 0; i < len; i++) {
            sums[i + 1] += sums[i] + (cs[i] == 'B' ? 1 : 0);
        }

        int max = Integer.MIN_VALUE;
        for (int i = k; i <= len; i++) {
            max = Math.max(max, sums[i] - sums[i - k]);
        }

        return k - max;
    }
}
