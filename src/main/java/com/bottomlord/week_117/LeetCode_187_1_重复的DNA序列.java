package com.bottomlord.week_117;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2021-10-08 08:30:14
 */
public class LeetCode_187_1_重复的DNA序列 {
    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();
        List<String> ans = new ArrayList<>();
        Map<String, Integer> mapping = new HashMap<>();
        for (int i = 0; i <= n - 10; i++) {
            String sub = s.substring(i, i + 10);
            mapping.put(sub, mapping.getOrDefault(sub, 0) + 1);

            if (mapping.get(sub) == 2) {
                ans.add(sub);
            }
        }

        return ans;
    }
}
