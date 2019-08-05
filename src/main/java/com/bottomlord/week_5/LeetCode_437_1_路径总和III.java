package com.bottomlord.week_5;

import com.bottomlord.TreeNode;

/**
 * @author LiveForExperience
 * @date 2019/8/5 21:52
 */
public class LeetCode_437_1_路径总和III {
    private int count = 0;
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return count;
        }

        dfs(root, sum);
        return count;
    }

    private void dfs(TreeNode node, int sum) {
        if (node == null) {
            return;
        }

        dfs(node.left, sum);

        doSearch(node.left, node.val, sum);
        doSearch(node.right, node.val, sum);

        dfs(node.right, sum);
    }

    private void doSearch(TreeNode node, int sum, int target) {
        if (node == null) {
            return;
        }

        sum += node.val;

        if (sum == target) {
            this.count++;
        }

        doSearch(node.left, sum, target);
        doSearch(node.right, sum, target);
    }
}
