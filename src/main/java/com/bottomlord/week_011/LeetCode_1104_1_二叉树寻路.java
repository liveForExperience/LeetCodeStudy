package com.bottomlord.week_011;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_1104_1_二叉树寻路 {
    public List<Integer> pathInZigZagTree(int label) {
        int level = 1;
        while (Math.pow(2, level) - 1 < label) {
            level+= 1;
        }
        List<Integer> ans = new ArrayList<>();
        ans.add(label);
        dfs(ans, level, label);
        return ans;
    }

    private void dfs(List<Integer> list, int level, int label) {
        if (level == 1) {
            return;
        }

        int sum = (int)Math.pow(2, level - 1) + (int)Math.pow(2, level) - 1;
        int num = (sum - label) / 2;
        list.add(0, num);
        dfs(list, level - 1, num);
    }
}
