package com.bottomlord.week_035;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ThinkPad
 * @date 2020/3/2 9:05
 */
public class Interview_48_1_最长不含重复字符的子字符串 {
    public int lengthOfLongestSubstring(String s) {
        char[] cs = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int start = 0, max = 0;

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(cs[i]) && map.get(cs[i]) >= start) {
                max = Math.max(i - start, max);
                start = map.get(cs[i]) + 1;
            }
            map.put(cs[i], i);
        }

        return Math.max(max, s.length() - start);
    }
}
