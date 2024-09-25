package com.bottomlord.week_066;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/10/13 8:26
 */
public class LeetCode_298_2 {
    private int ans;
    public int longestConsecutive(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = dfs(node.left),
            right = dfs(node.right);

        left = node.left != null && node.left.val == node.val + 1 ? left + 1 : 1;
        right = node.right != null && node.right.val == node.val + 1 ? right + 1 : 1;

        ans = Math.max(ans, Math.max(left, right));

        return Math.max(left, right);
    }
}
