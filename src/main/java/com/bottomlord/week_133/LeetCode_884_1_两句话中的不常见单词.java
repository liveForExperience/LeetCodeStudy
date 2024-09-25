package com.bottomlord.week_133;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-01-30 09:08:39
 */
public class LeetCode_884_1_两句话中的不常见单词 {
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> map1 = countMap(s1), map2 = countMap(s2);
        List<String> strs = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            if (entry.getValue() != 1) {
                continue;
            }

            if (!map2.containsKey(entry.getKey())) {
                strs.add(entry.getKey());
            }
        }

        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            if (entry.getValue() != 1) {
                continue;
            }

            if (!map1.containsKey(entry.getKey())) {
                strs.add(entry.getKey());
            }
        }

        return strs.toArray(new String[0]);
    }

    private Map<String, Integer> countMap(String s1) {
        char[] cs = s1.toCharArray();
        Map<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (char c : cs) {
            if (c == ' ') {
                String str = sb.toString();
                if (str.length() == 0) {
                    continue;
                }

                map.put(str, map.getOrDefault(str, 0) + 1);
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }

        if (sb.length() != 0) {
            String str = sb.toString();
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        return map;
    }
}
