package com.bottomlord.week_095;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/5/7 10:11
 */
public class LeetCode_734_1_句子相似性 {
    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        int len1 = sentence1.length, len2 = sentence2.length;
        if (len1 != len2) {
            return false;
        }

        Map<String, Set<String>> mapping = new HashMap<>();
        for (List<String> pair : similarPairs) {
            String word1 = pair.get(0), word2 = pair.get(1);
            Set<String> set1 = mapping.getOrDefault(word1, new HashSet<>());
            Set<String> set2 = mapping.getOrDefault(word2, new HashSet<>());
            set1.add(word2);
            set2.add(word1);
            mapping.put(word1, set1);
            mapping.put(word2, set2);
        }

        for (int i = 0; i < sentence1.length; i++) {
            String word1 = sentence1[i], word2 = sentence2[i];
            if (Objects.equals(word1, word2)) {
                continue;
            }

            Set<String> set1 = mapping.get(word1), set2 = mapping.get(word2);
            if ((set1 != null && set1.contains(word2)) || (set2 != null && set2.contains(word1))) {
                continue;
            }

            return false;
        }

        return true;
    }
}
