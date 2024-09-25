package com.bottomlord.week_186;

import com.bottomlord.TreeNode;

/**
 * @author chen yue
 * @date 2023-02-03 13:26:21
 */
public class LeetCode_1145_1_二叉树着色游戏 {
    private int left = 0, right = 0;
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        return count(root, x) / 2 < Math.max(Math.max(left, right), n - (left + right) - 1);
    }

    private int count(TreeNode node, int num) {
        if (node == null) {
            return 0;
        }

        int left = count(node.left, num), right = count(node.right, num);
        if (node.val == num) {
            this.left = left;
            this.right = right;
        }

        return left + right + 1;
    }
}
