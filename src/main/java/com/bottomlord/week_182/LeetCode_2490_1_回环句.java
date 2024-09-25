package com.bottomlord.week_182;

import java.util.Objects;

/**
 * @author chen yue
 * @date 2023-01-07 06:26:18
 */
public class LeetCode_2490_1_回环句 {
    public boolean isCircularSentence(String sentence) {
        String[] words = sentence.split(" ");
        int n = words.length;
        String firstWord = words[0], lastWord = words[n - 1];
        if (!Objects.equals(lastWord.charAt(lastWord.length() - 1), firstWord.charAt(0))) {
            return false;
        }

        for (int i = 0; i < n - 1; i++) {
            String first = words[i], second = words[i + 1];
            if (!Objects.equals(first.charAt(first.length() - 1), second.charAt(0))) {
                return false;
            }
        }

        return true;
    }
}
