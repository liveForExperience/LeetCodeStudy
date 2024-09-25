package com.bottomlord.week_017;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_1238_2 {
    private int val;
    public List<Integer> circularPermutation(int n, int start) {
        this.val = start;
        List<Integer> ans = new ArrayList<Integer>(){{add(start);}};
        dfs(1 << (n - 1), ans);
        return ans;
    }

    private void dfs(int xor, List<Integer> ans) {
        if (xor == 0) {
            return;
        }

        dfs(xor >> 1, ans);
        this.val ^= xor;
        ans.add(this.val);
        dfs(xor >> 1, ans);
    }
}
