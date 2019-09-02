package com.bottomlord.week_007;

import com.bottomlord.TreeNode;

public class LeetCode_572_2 {
    private TreeNode node;
    public boolean isSubtree(TreeNode s, TreeNode t) {
        node = t;
        return dfs(s, t);
    }

    private boolean dfs(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }

        if (s == null || t == null) {
            return false;
        }

        return s.val == t.val ? dfs(s.left, t.left) && dfs(s.right, t.right) ||
                                dfs(s.left, t) || dfs(s.right, t) :
                                dfs(s.left, node) || dfs(s.right, node);
    }
}