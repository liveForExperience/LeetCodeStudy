package com.bottomlord.week_201;

import com.bottomlord.TreeNode;

/**
 * @author chen yue
 * @date 2023-05-20 22:08:39
 */
public class LeetCode_1373_1_二叉搜索子树的最大键值和 {
    private int ans = 0;
    public int maxSumBST(TreeNode root) {
        dfs(root);
        return ans;
    }

    private static class SubBst {
        private boolean isBst;
        private int max;
        private int min;
        private int sum;

        public SubBst(boolean isBst, int max, int min, int sum) {
            this.isBst = isBst;
            this.max = max;
            this.min = min;
            this.sum = sum;
        }
    }

    private SubBst dfs(TreeNode node) {
        if (node == null) {
            return new SubBst(true, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
        }

        SubBst left = dfs(node.left), right = dfs(node.right);
        if (left.isBst &&
            right.isBst &&
            left.max < node.val &&
            right.min > node.val) {
            int sum = left.sum + right.sum + node.val;
            ans = Math.max(ans, sum);
            return new SubBst(true, Math.max(right.max, node.val), Math.min(left.min, node.val), sum);
        }

        return new SubBst(false, 0, 0, 0);
    }
}
