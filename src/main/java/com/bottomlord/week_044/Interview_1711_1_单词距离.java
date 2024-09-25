package com.bottomlord.week_044;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/5/9 8:04
 */
public class Interview_1711_1_单词距离 {
    public int findClosest(String[] words, String word1, String word2) {
        Map<String, List<Integer>> map = new HashMap<>();
        int len = words.length;
        for (int i = 0; i < len; i++) {
            String word = words[i];
            List<Integer> list = map.getOrDefault(word, new ArrayList<>());
            list.add(i);
            map.put(word, list);
        }

        List<Integer> list1 = map.get(word1), list2 = map.get(word2);

        if (list1 == null || list2 == null) {
            return len;
        }

        int ans = len;
        for (Integer i1 : list1) {
            for (Integer i2 : list2) {
                ans = Math.min(Math.abs(i1 - i2), ans);
            }
        }

        return ans;
    }
}
