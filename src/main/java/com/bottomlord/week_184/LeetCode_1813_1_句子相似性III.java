package com.bottomlord.week_184;

import java.util.Objects;

/**
 * @author chen yue
 * @date 2023-01-16 22:24:17
 */
public class LeetCode_1813_1_句子相似性III {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        int i = 0, j = 0;

        String[] words1 = sentence1.split(" "),
                words2 = sentence2.split(" ");

        int n1 = words1.length, n2 = words2.length;
        while (i < n1 && i < n2 && Objects.equals(words1[i], words2[i])) {
            i++;
        }

        while (j < n1 - i && j < n2 - i && Objects.equals(words1[n1 - 1 - j], words2[n2 - 1 - j])) {
            j++;
        }

        return i + j == Math.min(words1.length, words2.length);
    }
}
