package com.bottomlord.week_039;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/4/1 8:15
 */
public class Interview_0808_1_有重复字符串的排列组合 {
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

        for (int i = 0; i < cs.length; i++) {
            if (i != 0 && cs[i] == cs[i - 1]) {
                continue;
            }

            if (!memo[i]) {
                memo[i] = true;
                backTrack(cs, path.append(cs[i]), memo, list);
                path.deleteCharAt(path.length() - 1);
                memo[i] = false;
            }
        }
    }
}
