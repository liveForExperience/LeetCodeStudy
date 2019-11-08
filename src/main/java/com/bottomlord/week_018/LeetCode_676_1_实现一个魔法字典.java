package com.bottomlord.week_018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_676_1_实现一个魔法字典 {
    class MagicDictionary {
        private Map<Integer, List<String>> map;
        public MagicDictionary() {
            map = new HashMap<>();
        }

        public void buildDict(String[] dict) {
            for (String word : dict) {
                map.computeIfAbsent(word.length(), x -> new ArrayList<>()).add(word);
            }
        }

        public boolean search(String word) {
            if (!map.containsKey(word.length())) {
                return false;
            }

            for (String letter : map.get(word.length())) {
                int count = 0;
                for (int i = 0; i < word.length(); i++) {
                    if (letter.charAt(i) != word.charAt(i)) {
                        if (++count > 1) {
                            break;
                        }
                    }
                }

                if (count == 1) {
                    return true;
                }
            }

            return false;
        }
    }
}
