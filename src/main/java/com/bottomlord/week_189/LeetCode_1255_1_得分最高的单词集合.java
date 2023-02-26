package com.bottomlord.week_189;

/**
 * @author chen yue
 * @date 2023-02-26 13:37:56
 */
public class LeetCode_1255_1_得分最高的单词集合 {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int n = words.length, sum = 0;
        int[] count = new int[26];
        for (char letter : letters) {
            count[letter - 'a']++;
        }

        for (int i = 1; i <= (1 << n); i++) {
            int[] curCount = new int[26];
            for (int j = 0; j < n; j++) {
                if (((1 << j) & i) == 0) {
                    continue;
                }

                String word = words[j];
                for (char c : word.toCharArray()) {
                    curCount[c - 'a']++;
                }
            }

            boolean flag = true;
            int curSum = 0;
            for (int j = 0; j < 26; j++) {
                if (curCount[j] > count[j]) {
                    flag = false;
                    break;
                }

                curSum += curCount[j] * score[j];
            }

            if (flag) {
                sum = Math.max(sum, curSum);
            }
        }

        return sum;
    }
}
