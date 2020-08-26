package com.bottomlord.week_060;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/8/26 8:36
 */
public class LeetCode_244_1_最短单词距离II {
    class WordDistance {
        private Map<String, List<Integer>> map;
        public WordDistance(String[] words) {
            this.map = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                List<Integer> list = map.getOrDefault(words[i], new ArrayList<>());
                list.add(i);
                map.put(words[i], list);
            }
        }

        public int shortest(String word1, String word2) {
            List<Integer> list1 = map.get(word1),
                          list2 = map.get(word2);

            int ans = Integer.MAX_VALUE;
            for (Integer i1 : list1) {
                for (Integer i2 : list2) {
                    ans = Math.min(ans, Math.abs(i1 - i2));
                }
            }

            return ans;
        }
    }
}
