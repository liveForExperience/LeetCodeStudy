package com.bottomlord.week_039;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2020/3/31 22:11
 */
public class Interview_0807_1_无重复字符串的排列组合 {
    public String[] permutation(String S) {
        Set<String> set = new HashSet<>();
        backTrack(S, new StringBuilder(), new boolean[S.length()], set);
        return set.toArray(new String[0]);
    }

    private void backTrack(String s, StringBuilder path, boolean[] memo, Set<String> set) {
        if (path.length() == s.length()) {
            set.add(path.toString());
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!memo[i]) {
                memo[i] = true;
                backTrack(s, path.append(s.charAt(i)), memo, set);
                path.deleteCharAt(path.length() - 1);
                memo[i] = false;
            }
        }
    }
}
