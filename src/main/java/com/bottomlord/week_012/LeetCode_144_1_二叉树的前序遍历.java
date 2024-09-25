package com.bottomlord.week_012;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_144_1_二叉树的前序遍历 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(ans, root);
        return ans;
    }

    private void dfs(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }

        list.add(node.val);
        dfs(list, node.left);
        dfs(list, node.right);
    }
}
