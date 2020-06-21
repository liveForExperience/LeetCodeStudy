package com.bottomlord.week_050;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/6/21 11:55
 */
public class LeetCode_124_1_二叉树的最大路径和 {
    private int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        getMax(root);
        return ans;
    }

    private int getMax(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int rootVal = node.val;

        int leftVal = Math.max(0, getMax(node.left)),
            rightVal = Math.max(0, getMax(node.right));

        ans = Math.max(ans, rootVal + leftVal + rightVal);

        return rootVal + Math.max(leftVal, rightVal);
    }
}
