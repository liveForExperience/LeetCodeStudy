package com.bottomlord.week_038;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/3/26 9:38
 */
public class Interview_0410_1_检查子树 {
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2 == null;
        }

        return dfs(t1, t2) || checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
    }

    private boolean dfs(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }

        if (t1 == null || t2 == null) {
            return false;
        }

        return t1.val == t2.val && dfs(t1.left, t2.left) && dfs(t1.right, t2.right);
    }
}
