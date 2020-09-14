package com.bottomlord.week_063;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/9/14 18:50
 */
public class LeetCode_270_2 {
    public int closestValue(TreeNode root, double target) {
        return dfs(root, target, root.val);
    }

    private int dfs(TreeNode node, double target, int best) {
        if (Math.abs(best - target) > Math.abs(node.val - target)) {
            best = node.val;
        }

        if (target > node.val) {
            if (node.right == null) {
                return best;
            } else {
                return dfs(node.right, target, best);
            }
        } else {
            if (node.left == null) {
                return best;
            } else {
                return dfs(node.left, target, best);
            }
        }
    }
}
