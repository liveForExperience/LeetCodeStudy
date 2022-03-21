package com.bottomlord.week_141;

import com.bottomlord.TreeNode;

import java.util.Set;

/**
 * @author chen yue
 * @date 2022-03-21 09:29:33
 */
public class LeetCode_653_2 {
    public boolean findTarget(TreeNode root, int k) {
        return dfs(root, k, root);
    }

    private boolean dfs(TreeNode node, int target, TreeNode root) {
        if (node == null) {
            return false;
        }

        int val = node.val;
        if (val * 2 != target && search(root, target - val)) {
            return true;
        }

        return dfs(node.left, target, root) || dfs(node.right, target, root);
    }

    private boolean search(TreeNode node, int target) {
        if (node == null) {
            return false;
        }

        int val = node.val;
        if (val == target) {
            return true;
        }

        return val < target ? search(node.right, target) : search(node.left, target);
    }
}
