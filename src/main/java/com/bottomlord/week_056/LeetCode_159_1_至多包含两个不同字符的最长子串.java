package com.bottomlord.week_056;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/7/27 18:45
 */
public class LeetCode_159_1_至多包含两个不同字符的最长子串 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int len = s.length(), head = 0, tail = 0, ans = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (tail < len) {
            if (map.size() < 3) {
                map.put(s.charAt(tail), tail++);
            }

            if (map.size() == 3) {
                int index = map.remove(s.charAt(Collections.min(map.values())));
                head = index + 1;
            }

            ans = Math.max(ans, tail - head + 1);
        }

        return ans;
    }
}
