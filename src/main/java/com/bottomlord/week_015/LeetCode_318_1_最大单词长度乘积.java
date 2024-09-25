package com.bottomlord.week_015;

public class LeetCode_318_1_最大单词长度乘积 {
    public int maxProduct(String[] words) {
        int len = words.length;
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                arr[i] |= 1 << (word.charAt(j) - 'a');
            }
        }

        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((arr[i] & arr[j]) == 0) {
                    max = Math.max(words[i].length() * words[j].length(), max);
                }
            }
        }
        return max;
    }
}
