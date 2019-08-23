package com.bottomlord.week_7;

import com.bottomlord.TreeNode;

public class LeetCode_572_1_另一个树的子树 {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }

        if (s == null || t == null) {
            return false;
        }

        if (s.val == t.val) {
            return dfs(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
        }

        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean dfs(TreeNode s, TreeNode t) {
        if (s == null) {
            return t == null;
        }

        if (t == null) {
            return false;
        }

        if (s.val != t.val) {
            return false;
        }

        return dfs(s.left, t.left) && dfs(s.right, t.right);
    }
}
