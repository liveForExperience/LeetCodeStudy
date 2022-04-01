package com.bottomlord.week_142;

import com.bottomlord.TreeNode;

/**
 * @author chen yue
 * @date 2022-04-01 11:20:59
 */
public class LeetCode_663_1_均匀树划分 {
    private boolean flag = false;
    public boolean checkEqualTree(TreeNode root) {
        int total = dfs1(root);

        if (total % 2 != 0) {
            return false;
        }

        dfs2(root, total);
        return flag;
    }

    private int dfs1(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return node.val + dfs1(node.left) + dfs1(node.right);
    }

    private int dfs2(TreeNode node, int total) {
        if (flag) {
            return Integer.MAX_VALUE;
        }

        if (node == null) {
            return Integer.MAX_VALUE;
        }

        int left = dfs2(node.left, total);
        if(total - left == left) {
            flag = true;
            return 0;
        }


        int right = dfs2(node.right, total);
        if (total - right == right) {
            flag = true;
            return 0;
        }

        return (left == Integer.MAX_VALUE ? 0 : left) + (right == Integer.MAX_VALUE ? 0 : right) + node.val;
    }
}
