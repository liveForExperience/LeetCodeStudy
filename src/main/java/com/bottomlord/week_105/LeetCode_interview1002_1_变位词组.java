package com.bottomlord.week_105;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_interview1002_1_变位词组 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = cal(str);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    private String cal(String str) {
        int[] bucket = new int[26];
        char[] cs = str.toCharArray();
        for (char c : cs) {
            bucket[c - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != 0) {
                sb.append('a' + i).append(bucket[i]);
            }
        }
        return sb.toString();
    }
}
