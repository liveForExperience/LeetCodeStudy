package com.bottomlord.week_061;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/8/31 8:57
 */
public class LeetCode_250_1_统计共值子树 {
    private int count = 0;

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root);
        return count;
    }

    private boolean dfs(TreeNode node) {
        if (node.left == null && node.right == null) {
            count++;
            return true;
        }

        boolean same = true;
        if (node.left != null) {
            same = dfs(node.left) && node.val == node.left.val;
        }

        if (node.right != null) {
            same = dfs(node.right) && same && node.val == node.right.val;
        }

        if (!same) {
            return false;
        }

        count++;
        return true;
    }
}
