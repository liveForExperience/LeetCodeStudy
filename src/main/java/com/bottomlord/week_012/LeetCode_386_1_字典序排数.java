package com.bottomlord.week_012;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_386_1_字典序排数 {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        dfs(ans, 1, n);
        return ans;
    }

    private void dfs(List<Integer> ans, int start, int n) {
        if (start > n) {
            return;
        }

        for (int i = 0; i < 10 && i + start <= n; i++) {
            if (start == 1 && i == 9) {
                continue;
            }
            ans.add(start + i);
            dfs(ans, (start + i) * 10, n);
        }
    }
}
