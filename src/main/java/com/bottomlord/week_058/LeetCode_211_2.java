package com.bottomlord.week_058;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/8/14 8:38
 */
public class LeetCode_211_2 {
    class WordDictionary {
        private Map<Integer, Set<String>> map;
        public WordDictionary() {
            this.map = new HashMap<>();
        }

        public void addWord(String word) {
            if (word == null) {
                return;
            }

            int len = word.length();
            if (map.containsKey(len)) {
                map.get(len).add(word);
            } else {
                Set<String> set = new HashSet<>();
                set.add(word);
                map.put(len, set);
            }
        }

        public boolean search(String word) {
            if (word == null) {
                return false;
            }

            int len = word.length();
            if (!map.containsKey(len)) {
                return false;
            }

            Set<String> set = map.get(len);
            if (set.contains(word)) {
                return true;
            }

            for (String str : set) {
                boolean flag = true;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == '.') {
                        continue;
                    }

                    if (str.charAt(i) != word.charAt(i)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    return true;
                }
            }

            return false;
        }
    }
}
