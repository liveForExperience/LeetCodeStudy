package com.bottomlord.week_228;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-11-26 11:00:29
 */
public class LeetCode_828_1_统计子串中的唯一字符 {
    public int uniqueLetterString(String s) {
        int len = s.length();
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, new ArrayList<>());
                map.get(c).add(-1);
            }
            map.get(c).add(i);
        }

        int ans = 0;
        for (Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            list.add(len);

            for (int i = 1; i < list.size() - 1; i++) {
                ans += (list.get(i) - list.get(i - 1)) * (list.get(i + 1) - list.get(i));
            }
        }

        return ans;
    }

    public static void main(String[] args) {

    }
}
