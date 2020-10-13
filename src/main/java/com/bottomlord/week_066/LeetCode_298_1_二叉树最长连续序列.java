package com.bottomlord.week_066;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/10/13 8:18
 */
public class LeetCode_298_1_二叉树最长连续序列 {
    public int longestConsecutive(TreeNode root) {
        return dfs(null, root, 0);
    }

    private int dfs(TreeNode pre, TreeNode node, int len) {
        if (node == null) {
            return len;
        }

        len = (pre != null && pre.val == node.val - 1) ? len + 1 : 1;
        return Math.max(len, Math.max(dfs(node, node.left, len), dfs(node, node.right, len)));
    }
}
