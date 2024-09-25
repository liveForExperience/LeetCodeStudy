package com.bottomlord.week_157;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-07-11 09:10:56
 */
public class LeetCode_676_1_实现一个魔法字典 {
    class MagicDictionary {
        private Map<Integer, List<String>> map;

        public MagicDictionary() {
            this.map = new HashMap<>();
        }

        public void buildDict(String[] dict) {
            for (String word : dict) {
                map.computeIfAbsent(word.length(), (x) -> new ArrayList<>()).add(word);
            }
        }

        public boolean search(String word) {
            if (!map.containsKey(word.length())) {
                return false;
            }

            for (String candidate : map.get(word.length())) {
                int count = 0;
                for (int i = 0; i < candidate.length(); i++) {
                    if (candidate.charAt(i) != word.charAt(i)) {
                        count++;
                    }

                    if (count > 1) {
                        break;
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
