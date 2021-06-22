package com.bottomlord.week_102;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/6/22 8:30
 */
public class LeetCode_offer38_2 {
    public String[] permutation(String s) {
        List<String> ans = new ArrayList<>();
        backTrack(s.toCharArray(), 0, ans);
        return ans.toArray(new String[0]);
    }

    private void backTrack(char[] cs, int index, List<String> ans) {
        if (index == cs.length - 1) {
            ans.add(new String(cs));
            return;
        }

        boolean[] used = new boolean[256];
        for (int i = index; i < cs.length; i++) {
            if (used[cs[i]]) {
                continue;
            }

            used[cs[i]] = true;
            swap(cs, index, i);
            backTrack(cs, index + 1, ans);
            swap(cs,index, i);
        }
    }

    private void swap(char[] cs, int x, int y) {
        char c = cs[x];
        cs[x] = cs[y];
        cs[y] = c;
    }
}
