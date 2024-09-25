package com.bottomlord.week_007;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_501_2 {
    private Integer pre;
    private int count = 0;
    private int mode = 0;
    private List<Integer> list = new ArrayList<>();
    public int[] findMode(TreeNode root) {
        dfs(root);
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        dfs(node.left);
        if (pre == null || node.val != pre) {
            count = 1;
        } else {
            count++;
        }

        if (count > mode) {
            mode = count;
            list.clear();
            list.add(node.val);
        } else if (count == mode) {
            list.add(node.val);
        }

        pre = node.val;
        dfs(node.right);
    }
}