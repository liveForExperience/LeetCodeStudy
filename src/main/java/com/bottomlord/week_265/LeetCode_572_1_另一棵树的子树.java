package com.bottomlord.week_265;

import com.bottomlord.TreeNode;

/**
 * @author chen yue
 * @date 2024-08-04 11:48:22
 */
public class LeetCode_572_1_另一棵树的子树 {
    private TreeNode node;

    public boolean isSubtree(TreeNode s, TreeNode t) {
        node = t;
        return dfs(s);
    }

    private boolean dfs(TreeNode s) {
        if (s == null) {
            return node == null;
        }

        boolean result = dfs2(s, node);
        if (result) {
            return result;
        }

        return dfs(s.left) || dfs(s.right);
    }

    private boolean dfs2(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }

        if (s == null || t == null) {
            return false;
        }

        return s.val == t.val && dfs2(s.left, t.left) && dfs2(s.right, t.right);
    }
}
