package com.bottomlord.week_093;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2021/4/21 8:21
 */
public class LeetCode_91_1_解码方法 {
    private Map<String, String> map = new HashMap<>();

    {
        char c = 'A';
        for (int i = 1; i <= 26; i++) {
            map.put("" + i, "" + c);
            c = (char) ((int) c + 1);
        }
    }

    public int numDecodings(String s) {
        return backTrack(s, 0, new StringBuilder(), new HashSet<>());
    }

    private int backTrack(String s, int index, StringBuilder sb, Set<String> memo) {
        if (index == s.length()) {
            return 1;
        }

        int ans = 0, len = sb.length();
        String one = "" + s.charAt(index);
        if (map.containsKey(one)) {
            sb.append(one);
            if (memo.contains(sb.toString())) {
                sb.setLength(len);
            } else {
                memo.add(sb.toString());
                ans += backTrack(s, index + 1, sb, memo);
                sb.setLength(len);
            }
        }

        if (index < s.length() - 1) {
            String two = one + s.charAt(index + 1);
            if (map.containsKey(two)) {
                sb.append(two);
                if (memo.contains(sb.toString())) {
                    sb.setLength(len);
                } else {
                    memo.add(sb.toString());
                    ans += backTrack(s, index + 2, sb, memo);
                    sb.setLength(len);
                }
            }
        }

        return ans;
    }
}
