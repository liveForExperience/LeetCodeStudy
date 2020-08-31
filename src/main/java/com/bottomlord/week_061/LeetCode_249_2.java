package com.bottomlord.week_061;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ChenYue
 * @date 2020/8/31 8:49
 */
public class LeetCode_249_2 {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            String shift = shift(str);
            List<String> list = map.getOrDefault(shift, new ArrayList<>());
            list.add(str);
            map.put(shift, list);
        }

        return new ArrayList<>(map.values());
    }

    private String shift(String str) {
        int bit = str.charAt(0) - 'a';
        char[] cs = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            cs[i] = cs[i] - (char)bit - 'a' >= 0 ? (char)(cs[i] - (char)bit) : (char)(cs[i] - (char)(bit - 26));
        }
        return new String(cs);
    }
}
