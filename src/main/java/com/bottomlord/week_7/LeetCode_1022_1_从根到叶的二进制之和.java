package com.bottomlord.week_7;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_1022_1_从根到叶的二进制之和 {
    public int sumRootToLeaf(TreeNode root) {
        List<String> list = new ArrayList<>();
        dfs(root, "", list);

        int sum = 0;
        for (String str : list) {
            sum += Integer.parseInt(str, 2);
        }
        return sum;
    }

    private void dfs(TreeNode node, String str, List<String> list) {
        if (node == null) {
            return;
        }

        String val = str + node.val;
        if (node.left == null && node.right == null) {
            list.add(val);
        }

        dfs(node.left, val, list);
        dfs(node.right, val, list);
    }
}
