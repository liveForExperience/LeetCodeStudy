package com.bottomlord.week_010;

import java.util.HashSet;
import java.util.Set;

public class LeetCode_1079_1_活字印刷 {
    public int numTilePossibilities(String tiles) {
        Set<String> set = new HashSet<>();
        backTrack(tiles.toCharArray(), new boolean[tiles.length()], set, new char[tiles.length()], 0);
        return set.size();
    }

    private void backTrack(char[] cs, boolean[] visit, Set<String> set, char[] c, int index) {
        if (index == cs.length) {
            return;
        }

        for (int i = 0; i < cs.length; i++) {
            if (!visit[i]) {
                c[index] = cs[i];
                set.add(new String(c).trim());
                visit[i] = true;
                backTrack(cs, visit, set, c, index + 1);
                c[index] = ' ';
                visit[i] = false;
            }
        }
    }
}
