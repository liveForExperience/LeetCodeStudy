package com.bottomlord.week_010;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_77_2 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(ans, new ArrayList<>(), n, k, 1, 0);
        return ans;
    }

    private void backTrack(List<List<Integer>> ans, List<Integer> list, int n, int k, int num, int time) {
        if (time == k) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = num; i <= n; i++) {
            list.add(i);
            backTrack(ans, list, n, k, i + 1, time + 1);
            list.remove(list.size() - 1);
        }
    }
}
