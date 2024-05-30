package com.bottomlord.week_255;

/**
 * @author chen yue
 * @date 2024-05-29 14:59:23
 */
public class LeetCode_2981_1_找出出现至少三次的最长特殊子字符串I {
    public int maximumLength(String s) {
        int[][] matrix = new int[26][51];
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            int len = 0;
            while (i + len < n && cs[i + len] == c) {
                len++;
                matrix[c - 'a'][len]++;
            }
        }

        int max = -1;
        for (int[] arr : matrix) {
            for (int len = 0; len < arr.length; len++) {
                if (arr[len] < 3) {
                    continue;
                }

                max = Math.max(max, len);
            }
        }

        return max;
    }
}
