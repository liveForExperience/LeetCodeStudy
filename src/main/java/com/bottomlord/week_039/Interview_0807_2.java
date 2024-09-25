package com.bottomlord.week_039;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/3/31 22:34
 */
public class Interview_0807_2 {
    public String[] permutation(String S) {
        char[] cs = S.toCharArray();
        Arrays.sort(cs);
        List<String> list = new LinkedList<>();
        backTrack(cs, new StringBuilder(), new boolean[cs.length], list);
        return list.toArray(new String[0]);
    }

    private void backTrack(char[] cs, StringBuilder path, boolean[] memo, List<String> list) {
        if (path.length() == cs.length) {
            list.add(path.toString());
            return;
        }

        char lastUsed = ' ';
        for (int i = 0; i < cs.length; i++) {
            if (!memo[i] && cs[i] != lastUsed) {
                lastUsed = cs[i];
                memo[i] = true;
                backTrack(cs, path.append(cs[i]), memo, list);
                path.deleteCharAt(path.length() - 1);
                memo[i] = false;
            }
        }
    }
}
