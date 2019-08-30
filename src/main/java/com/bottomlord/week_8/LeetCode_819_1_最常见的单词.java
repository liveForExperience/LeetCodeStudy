package com.bottomlord.week_8;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_819_1_最常见的单词 {
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (char c : paragraph.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                sb.append(c);
            } else {
                String str = sb.toString();
                if ("".equals(str)) {
                    continue;
                }
                map.put(str, map.getOrDefault(str, 0) + 1);
                sb = new StringBuilder();
            }
        }

        String str = sb.toString();
        if (!"".equals(str)) {
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
        }

        String max = null;
        for (String word : map.keySet()) {
            if (max == null) {
                max = validate(max, word, banned);
            } else {
                max = map.get(max) < map.get(word) ? validate(max, word, banned) : max;
            }
        }
        return max;
    }

    private String validate(String max, String str, String[] banned) {
        boolean noMatch = true;
        for (String banStr : banned) {
            if (banStr.equals(str)) {
                noMatch = false;
                break;
            }
        }
        return noMatch ? str : max;
    }
}
