package com.bottomlord.week_034;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/2/28 9:07
 */
public class Interview_38_2 {
    public String[] permutation(String s) {
        List<String> list = new ArrayList<>();
        backTrack(s.toCharArray(), 0, list);
        return list.toArray(new String[0]);
    }

    private void backTrack(char[] cs, int index, List<String> list) {
        if (index == cs.length - 1) {
            list.add(new String(cs));
            return;
        }

        boolean[] used = new boolean[256];
        for (int i = index; i < cs.length; i++) {
            if (used[cs[i]]) {
                continue;
            }

            used[cs[i]] = true;
            swap(cs, index, i);
            backTrack(cs, index + 1, list);
            swap(cs, index, i);
        }

    }

    private void swap(char[] cs, int x, int y) {
        char tmp = cs[x];
        cs[x] = cs[y];
        cs[y] = tmp;
    }
}