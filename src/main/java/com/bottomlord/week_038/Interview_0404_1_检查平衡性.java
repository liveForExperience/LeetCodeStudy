package com.bottomlord.week_038;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/3/24 8:17
 */
public class Interview_0404_1_检查平衡性 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        int left = dfs(root.left), right = dfs(root.right);
        if (Math.abs(left - right) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = dfs(node.left), right = dfs(node.right);

        return Math.max(left, right) + 1;
    }
}
