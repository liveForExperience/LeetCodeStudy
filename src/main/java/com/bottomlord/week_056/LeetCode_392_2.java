package com.bottomlord.week_056;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/7/27 8:38
 */
public class LeetCode_392_2 {
    public boolean isSubsequence(String s, String t) {
        Map<Character, List<Integer>> map =  new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            List<Integer> list = map.getOrDefault(c, new ArrayList<>());
            list.add(i);
            map.put(c, list);
        }

        int pre = -1;
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            }

            List<Integer> list = map.get(c);
            int tmp = pre;
            for (Integer index : list) {
                if (index > tmp) {
                    pre = index;
                    break;
                }
            }

            if (tmp == pre) {
                return false;
            }
        }

        return true;
    }
}
