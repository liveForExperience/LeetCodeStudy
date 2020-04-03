package com.bottomlord.week_039;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/4/3 12:49
 */
public class Interview_1002_1_变位词组 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] cs = str.toCharArray();
            Arrays.sort(cs);
            String s = new String(cs);

            List<String> list = map.getOrDefault(s, new ArrayList<>());
            list.add(str);
            map.put(s, list);
        }

        return new ArrayList<>(map.values());
    }
}
