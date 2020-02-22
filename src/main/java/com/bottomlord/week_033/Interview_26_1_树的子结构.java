package com.bottomlord.week_033;

import com.bottomlord.TreeNode;

/**
 * @author ThinkPad
 * @date 2020/2/22 20:14
 */
public class Interview_26_1_树的子结构 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }

        return dfs(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean dfs(TreeNode a, TreeNode b) {
        if (a == null) {
            return false;
        }

        if (a.val == b.val) {
            boolean ans = true;
            if (b.left != null) {
                ans = dfs(a.left, b.left);
            }

            if (b.right != null) {
                ans = ans && dfs(a.right, b.right);
            }

            return ans;
        }

        return false;
    }
}
