package com.bottomlord.week_5;

import com.bottomlord.TreeNode;

/**
 * @author LiveForExperience
 * @date 2019/8/5 13:19
 */
public class LeetCode_653_2 {
    private TreeNode node;
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        if (node == null) {
            node = root;
        }

        int result = k - root.val;
        if (root.val == result) {
            return findTarget(root.left, k) || findTarget(root.right, k);
        }

        boolean find = dfs(this.node, result);
        if (find) {
            return true;
        }

        return findTarget(root.left, k) || findTarget(root.right, k);
    }

    private boolean dfs(TreeNode node, int target) {
        if (node == null) {
            return false;
        }

        if (node.val == target) {
            return true;
        }

        return node.val > target ? dfs(node.left, target) : dfs(node.right, target);
    }
}