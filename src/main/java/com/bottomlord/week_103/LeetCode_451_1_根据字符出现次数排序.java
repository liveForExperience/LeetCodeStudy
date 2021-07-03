package com.bottomlord.week_103;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCode_451_1_根据字符出现次数排序 {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] cs = s.toCharArray();
        for (char c : cs) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        Map.Entry<Character, Integer>[] entries = new Map.Entry[map.size()];
        int index = 0;
        for (Map.Entry<Character, Integer> characterIntegerEntry : map.entrySet()) {
            entries[index++] = characterIntegerEntry;
        }
        Arrays.sort(entries, (x,y) -> y.getValue() - x.getValue());
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : entries) {
            int time = entry.getValue();
            while (time-- > 0) {
                sb.append(entry.getKey());
            }
        }

        return sb.toString();
    }
}
