package com.bottomlord.week_013;

import java.util.*;

public class LeetCode_451_1_根据字符出现频率排序 {
    public String frequencySort(String s) {
        char[] cs = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : cs) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        List<Dict> dicts = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            dicts.add(new Dict(entry));
        }

        dicts.sort((x, y) -> y.num - x.num);

        StringBuilder sb = new StringBuilder();
        for (Dict dict : dicts) {
            for (int i = 0; i < dict.num; i++) {
                sb.append(dict.c);
            }
        }

        return sb.toString();
    }

    class Dict {
        private Character c;
        private Integer num;

        public Dict(Map.Entry<Character, Integer> entry) {
            c = entry.getKey();
            num = entry.getValue();
        }
    }
}
