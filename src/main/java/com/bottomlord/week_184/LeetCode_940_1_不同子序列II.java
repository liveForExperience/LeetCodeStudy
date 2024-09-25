package com.bottomlord.week_184;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-10-16 16:27:37
 */
public class LeetCode_940_1_不同子序列II {
    public int distinctSubseqII(String s) {
        Set<String> set = new HashSet<>();
        backTrack(s, 0, new StringBuilder(), set);
        return set.size();
    }

    private void backTrack(String s, int index, StringBuilder sb, Set<String> memo) {
        if (index >= s.length()) {
            memo.add(sb.toString());
            return;
        }

        for (int i = index; i < s.length(); i++) {
            int len = sb.length();
            sb.append(s.charAt(i));
            memo.add(sb.toString());
            backTrack(s, i + 1, sb, memo);
            sb.setLength(len);
        }
    }
}
