package com.bottomlord.week_092;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/4/14 8:51
 */
public class LeetCode_522_1_最长特殊序列II {
    public int findLUSlength(String[] strs) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : strs) {
            Set<String> set = new HashSet<>();
            backTrack(str, 0, new StringBuilder(), set);
            for (String result : set) {
                map.put(result, map.getOrDefault(result, 0) + 1);
            }
        }

        int ans = -1;
        for (String key : map.keySet()) {
            if (map.get(key) == 1) {
                ans = Math.max(ans, key.length());
            }
        }
        return ans;
    }

    private void backTrack(String str, int index, StringBuilder sb, Set<String> set) {
        if (index == str.length()) {
            return;
        }

        for (int i = index; i < str.length(); i++) {
            int len = sb.length();

            sb.append(str.charAt(i));
            if (!set.contains(sb.toString())) {
                set.add(sb.toString());
                backTrack(str, i + 1, sb, set);
            }

            sb.setLength(len);
        }
    }
}
