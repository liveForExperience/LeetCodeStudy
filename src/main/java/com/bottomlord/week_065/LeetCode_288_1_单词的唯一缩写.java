package com.bottomlord.week_065;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2020/9/29 8:24
 */
public class LeetCode_288_1_单词的唯一缩写 {
    class ValidWordAbbr {
        private Map<String, Set<String>> map;
        public ValidWordAbbr(String[] dictionary) {
            this.map = new HashMap<>();
            for (String word : dictionary) {
                String sWord = simplify(word);
                Set<String> set = this.map.getOrDefault(sWord, new HashSet<>());
                set.add(word);
                this.map.put(sWord, set);
            }
        }

        public boolean isUnique(String word) {
            String sWord = simplify(word);
            if (this.map.containsKey(sWord)) {
                Set<String> words = this.map.get(sWord);
                return words.size() == 1 && words.contains(word);
            }

            return true;
        }

        private String simplify(String word) {
            if (word.length() <= 2) {
                return word;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(word.charAt(0)).append(word.length() - 2).append(word.charAt(word.length() - 1));
            return sb.toString();
        }
    }
}
