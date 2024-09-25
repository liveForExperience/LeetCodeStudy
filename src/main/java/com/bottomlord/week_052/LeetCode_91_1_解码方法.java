package com.bottomlord.week_052;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2020/7/4 14:03
 */
public class LeetCode_91_1_解码方法 {
    public int numDecodings(String s) {
        Set<String> set = new HashSet<>();
        backTrack(s, new StringBuilder(), new HashSet<>(), set);
        return set.size();
    }

    private void backTrack(String s, StringBuilder sb, Set<String> memo, Set<String> ans) {
        if (s.length() == 0) {
            ans.add(sb.toString());
        }

        if (memo.contains(s)) {
            return;
        }

        memo.add(s);

        sb.append((char)(s.charAt(0) + 'A' - 1));
        backTrack(s.substring(1), sb, memo, ans);
        sb.deleteCharAt(sb.length() - 1);

        if (s.length() == 1) {
            return;
        }

        int num = (Integer.parseInt(s.substring(0, 2)));
        if (num < 1 || num > 26) {
            return;
        }

        sb.append((char)(Integer.parseInt(s.substring(0, 2)) + 'A' - 1));
        backTrack(s.substring(2), sb, memo, ans);
        sb.deleteCharAt(sb.length() - 1);
    }
}
