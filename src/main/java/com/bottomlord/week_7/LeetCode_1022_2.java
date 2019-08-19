package com.bottomlord.week_7;

import com.bottomlord.TreeNode;

public class LeetCode_1022_2 {
    int ans = 0;
    public int sumRootToLeaf(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root, 0);

        return ans;
    }

    private void dfs(TreeNode node, int sum) {
        int val = 2 * sum + node.val;
        if (node.left == null && node.right == null) {
            ans += val;
        }

        if (node.right != null) {
            dfs(node.right, val);
        }

        if (node.left != null) {
            dfs(node.left, val);
        }
    }
}