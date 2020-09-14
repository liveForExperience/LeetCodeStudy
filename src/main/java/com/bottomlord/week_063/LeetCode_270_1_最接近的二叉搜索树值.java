package com.bottomlord.week_063;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/9/14 18:34
 */
public class LeetCode_270_1_最接近的二叉搜索树值 {
    private double minDiff = Double.MAX_VALUE;
    private int min = Integer.MAX_VALUE;
    public int closestValue(TreeNode root, double target) {
        dfs(root, target);
        return min;
    }

    private void dfs(TreeNode node, double target) {
        if (node == null) {
            return;
        }

        double diff = Math.abs(node.val - target);
        if (diff < minDiff) {
            minDiff = diff;
            min = node.val;
        }

        if (node.val > target) {
            dfs(node.left, target);
        } else {
            dfs(node.right, target);
        }
    }
}
