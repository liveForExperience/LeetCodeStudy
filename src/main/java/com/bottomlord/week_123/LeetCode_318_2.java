package com.bottomlord.week_123;

/**
 * @author chen yue
 * @date 2021-11-17 08:51:36
 */
public class LeetCode_318_2 {
    public int maxProduct(String[] words) {
        int len = words.length;
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            String word = words[i];
            for (char c : word.toCharArray()) {
                arr[i] |= 1 << (c - 'a');
            }
        }

        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((arr[i] & arr[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }

        return max;
    }
}
