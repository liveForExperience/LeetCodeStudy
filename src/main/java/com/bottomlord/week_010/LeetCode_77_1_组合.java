package com.bottomlord.week_010;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_77_1_组合 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(ans, new ArrayList<>(), new boolean[n + 1], n, k, 1, 0);
        return ans;
    }

    private void backTrack(List<List<Integer>> ans, List<Integer> list, boolean[] visit, int n, int k, int num, int time) {
        if (time == k) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = num; i <= n; i++) {
            if (!visit[i]) {
                list.add(i);
                visit[i] = true;
                backTrack(ans, list, visit, n, k, i + 1, time + 1);
                visit[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }
}
