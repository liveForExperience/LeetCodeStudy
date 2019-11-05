package com.bottomlord.contest_20191027;

import java.util.ArrayList;
import java.util.List;

public class Contest_2_循环码排列 {
    private int value;
    public List<Integer> circularPermutation(int n, int start) {
        this.value = start;
        List<Integer> ans = new ArrayList<>();
        dfs(1 << (n - 1), ans);
        return ans;
    }

    private void dfs(int head, List<Integer> ans) {
        if (head == 0) {
            return;
        }

        dfs(head >> 1, ans);
        this.value ^= head;
        ans.add(value);
        dfs(head >> 1, ans);
    }
}
