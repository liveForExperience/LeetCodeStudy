package com.bottomlord.week_014;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_129_1_求根到叶子节点数字之和 {
    public int sumNumbers(TreeNode root) {
        List<String> list = new ArrayList<>();
        dfs(root, "", list);
        int ans = 0;
        for (String str : list) {
            ans += Integer.parseInt(str);
        }
        return ans;
    }

    private void dfs(TreeNode node, String num, List<String> list) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            list.add(num + node.val);
            return;
        }

        dfs(node.left, num + node.val, list);
        dfs(node.right, num + node.val, list);
    }
}
