package com.bottomlord.week_114;

import java.util.Objects;

/**
 * @author chen yue
 * @date 2021-09-13 20:19:52
 */
public class LeetCode_1668_1_最大重复子字符串 {
    public int maxRepeating(String sequence, String word) {
        int wordLen = word.length(), max = 0;
        for (int i = 0; i < sequence.length(); i++) {
            int count = 0;
            for (int j = i; j < sequence.length() && j + wordLen <= sequence.length();) {
                String sub = sequence.substring(j, j + wordLen);
                if (Objects.equals(sub, word)) {
                    count++;
                    j += wordLen;
                } else {
                    max = Math.max(max, count);
                    count = 0;
                    j++;
                }
            }

            max = Math.max(max, count);
        }
        return max;
    }
}
