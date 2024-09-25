package com.bottomlord.week_004;

import com.bottomlord.TreeNode;

/**
 * @author LiveForExperience
 * @date 2019/8/3 16:19
 */
public class LeetCode_530_2 {
    private Integer pre = null;
    private Integer min = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return min;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        dfs(node.left);

        if (pre != null) {
            min = Math.min(min, Math.abs(node.val - pre));
        }

        pre = node.val;
        dfs(node.right);
    }
}
