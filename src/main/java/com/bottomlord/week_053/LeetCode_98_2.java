package com.bottomlord.week_053;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/7/6 8:52
 */
public class LeetCode_98_2 {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, null, null);
    }

    private boolean dfs(TreeNode node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }

        int val = node.val;
        if (min != null && val <= min) {
            return false;
        }

        if (max != null && val >= max) {
            return false;
        }

        if (!dfs(node.left, min, val)) {
            return false;
        }

        if (!dfs(node.right, val, max)) {
            return false;
        }

        return true;
    }
}
