package com.bottomlord.week_010;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_94_1_二叉树的中序遍历 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(list, root);
        return list;
    }

    private void dfs(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }

        dfs(list, node.left);
        list.add(node.val);
        dfs(list, node.right);
    }
}
