package com.bottomlord.week_094;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2021/4/30 10:58
 */
public class LeetCode_555_1_分割连接字符串 {
    public String splitLoopedString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        Set<String> set = new HashSet<>();
        backTrack(strs, 0, new StringBuilder(), set);

        String ans = null;
        for (String str : set) {
            for (int i = 0; i < str.length(); i++) {
                int index = 0;
                StringBuilder sb = new StringBuilder();
                while (index < str.length()) {
                    sb.append(str.charAt((i + index) % str.length()));
                    index++;
                }

                ans = max(sb.toString(), ans);
            }
        }

        return ans;
    }

    private void backTrack(String[] strs, int index, StringBuilder sb, Set<String> list) {
        if (index == strs.length) {
            list.add(sb.toString());
            return;
        }

        int len = sb.length();
        sb.append(strs[index]);
        backTrack(strs, index + 1, sb, list);
        sb.setLength(len);
        sb.append(reverse(strs[index]));
        backTrack(strs, index + 1, sb, list);
        sb.setLength(len);
    }

    private String reverse(String str) {
        char[] cs = str.toCharArray();
        int len = cs.length;
        for (int i = 0; i < len / 2; i++) {
            char tmp = cs[i];
            cs[i] = cs[len - i - 1];
            cs[len - i - 1] = tmp;
        }
        return new String(cs);
    }

    private String max(String a, String b) {
        if (a == null) {
            return b;
        }

        if (b == null) {
            return a;
        }

        return a.compareTo(b) > 0 ? a : b;
    }
}
