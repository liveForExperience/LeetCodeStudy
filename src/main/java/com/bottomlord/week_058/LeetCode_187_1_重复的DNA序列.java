package com.bottomlord.week_058;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/8/11 8:19
 */
public class LeetCode_187_1_重复的DNA序列 {
    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> map = new HashMap<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String word = s.substring(i, i + 10);
            map.put(word, map.getOrDefault(word, 0) + 1);
            if (map.get(word) == 2) {
                ans.add(word);
            }
        }
        return ans;
    }
}
