package com.bottomlord.week_8;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_290_1_单词规律 {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> cMap = new HashMap<>();
        Map<String, Character> sMap = new HashMap<>();

        char[] cs = pattern.toCharArray();
        String[] ss = str.split(" ");

        if (cs.length != ss.length) {
            return false;
        }

        for (int i = 0; i < ss.length; i++) {
            Character c = cs[i];
            String s = ss[i];

            if (!cMap.containsKey(c) && !sMap.containsKey(c)) {
                cMap.put(c, s);
                sMap.put(s, c);
                continue;
            }

            if (cMap.containsKey(c) && sMap.containsKey(s)) {
                if (cMap.get(c).equals(s) && sMap.get(s).equals(c)) {
                    continue;
                }
            }

            return false;
        }

        return true;
    }
}
