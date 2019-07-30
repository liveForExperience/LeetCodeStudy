package com.bottomlord.week_4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiveForExperience
 * @date 2019/7/30 13:24
 */
public class LeetCode_884_1_两句话中的不常见单词 {
    public String[] uncommonFromSentences(String A, String B) {
        Map<String, Integer> map = new HashMap<>();
        String[] as = A.split(" ");
        String[] bs = B.split(" ");

        compute(as, map);
        compute(bs, map);

        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            if (entry.getValue() == 1) {
                ans.add(entry.getKey());
            }
        }

        return ans.toArray(new String[0]);
    }

    private void compute(String[] s, Map<String, Integer> map) {
        for (String a : s) {
            if (map.containsKey(a)) {
                map.computeIfPresent(a, (k, v) -> v += 1);
            } else {
                map.put(a, 1);
            }
        }
    }
}
