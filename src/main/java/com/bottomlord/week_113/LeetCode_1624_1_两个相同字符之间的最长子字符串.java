package com.bottomlord.week_113;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2021-09-06 21:52:08
 */
public class LeetCode_1624_1_两个相同字符之间的最长子字符串 {
    public int maxLengthBetweenEqualCharacters(String s) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.computeIfAbsent(s.charAt(i), x -> new ArrayList<>()).add(i);
        }

        int ans = -1;
        for (Character c : map.keySet()) {
            List<Integer> list = map.get(c);

            if (list.size() <= 1) {
                continue;
            }

            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int num : list) {
                min = Math.min(num, min);
                max = Math.max(num, max);
            }

            ans = Math.max(ans, max - min - 1);
        }

        return ans;
    }
}
