package com.bottomlord.week_8;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_290_2 {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        String[] ss = str.split(" ");
        if (pattern.length() != ss.length) {
            return false;
        }

        for (int i = 0; i < ss.length; i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(ss[i])) {
                    return false;
                }
            } else if (map.containsValue(ss[i])) {
                return false;
            } else {
                map.put(c, ss[i]);
            }
        }
        return true;
    }
}