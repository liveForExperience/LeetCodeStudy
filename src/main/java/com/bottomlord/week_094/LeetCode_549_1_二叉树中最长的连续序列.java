package com.bottomlord.week_094;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2021/4/29 9:22
 */
public class LeetCode_549_1_二叉树中最长的连续序列 {
    private int ans = 0;

    public int longestConsecutive(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int inc = 1, dec = 1;
        if (node.left != null) {
            int[] result = dfs(node.left);
            if (node.val == node.left.val - 1) {
                inc = result[0] + 1;
            }

            if (node.val == node.left.val + 1) {
                dec = result[1] + 1;
            }
        }

        if (node.right != null) {
            int[] result = dfs(node.right);
            if (node.val == node.right.val - 1) {
                inc = Math.max(inc, result[0] + 1);
            }

            if (node.val == node.right.val + 1) {
                dec = Math.max(dec, result[1] + 1);
            }
        }

        ans = Math.max(ans, inc + dec - 1);
        return new int[]{inc, dec};
    }
}
