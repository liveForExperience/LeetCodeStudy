package com.bottomlord.week_136;

import com.bottomlord.TreeNode;

/**
 * @author chen yue
 * @date 2022-02-16 10:49:23
 */
public class LeetCode_offerII59_2 {
    public boolean findTarget(TreeNode root, int k) {
        return dfs(root, root, k);
    }

    private boolean dfs(TreeNode node, TreeNode root, int k) {
        if (node == null) {
            return false;
        }

        int val = node.val, target = k - val;
        if (val != target && find(root, target)) {
            return true;
        }

        return dfs(node.left, root, k) || dfs(node.right, root, k);
    }

    private boolean find(TreeNode node, int target) {
        if (node == null) {
            return false;
        }

        if (node.val == target) {
            return true;
        }

        if (node.val > target) {
            return find(node.left, target);
        } else {
            return find(node.right, target);
        }
    }
}
