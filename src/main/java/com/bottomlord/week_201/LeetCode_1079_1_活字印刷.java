package com.bottomlord.week_201;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2023-05-19 23:21:33
 */
public class LeetCode_1079_1_活字印刷 {
    public int numTilePossibilities(String tiles) {
        char[] cs = tiles.toCharArray();
        Set<String> set = new HashSet<>();
        backTrack(0, cs, new boolean[cs.length], new StringBuilder(), set);
        return set.size();
    }

    private void backTrack(int index, char[] cs, boolean[] memo, StringBuilder sb, Set<String> set) {
        if (index >= cs.length) {
            return;
        }

        for (int i = 0; i < cs.length; i++) {
            if (memo[i]) {
                continue;
            }

            int len = sb.length();
            memo[i] = true;
            sb.append(cs[i]);
            set.add(sb.toString());
            backTrack(index + 1, cs, memo, sb, set);
            sb.setLength(len);
            memo[i] = false;
        }
    }
}
