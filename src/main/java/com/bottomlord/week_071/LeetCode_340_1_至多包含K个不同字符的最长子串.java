package com.bottomlord.week_071;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2020/11/17 17:43
 */
public class LeetCode_340_1_至多包含K个不同字符的最长子串 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int x = 0, y =0, max = 0, sum = 0;
        Map<Character, Integer> map = new HashMap<>();
        char[] cs = s.toCharArray();
        while (y < cs.length) {
            map.put(cs[y], map.getOrDefault(cs[y], 0) + 1);
            y++;
            sum++;
            if (map.size() <= k) {
                max = Math.max(max, sum);
            } else {
                while (x < y) {
                    sum--;
                    map.put(cs[x], map.get(cs[x]) - 1);
                    if (map.get(cs[x]) <= 0) {
                        map.remove(cs[x]);
                    }
                    x++;
                    if (map.size() <= k) {
                        break;
                    }
                }
            }
        }
        return max;
    }
}
