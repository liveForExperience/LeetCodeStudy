package com.bottomlord.week_1;

import com.bottomlord.TreeNode;

/**
 * @author LiveForExperience
 * @date 2019/7/13 19:02
 */
public class LeetCode_700_1_二叉搜索树中的搜索 {
    public TreeNode searchBST(TreeNode root, int val) {
        return dfs(root, val);
    }

    private TreeNode dfs(TreeNode node, int val) {
        if (node == null || node.val == val) {
            return node;
        }

        if (node.val < val) {
            return dfs(node.right, val);
        } else {
            return dfs(node.left, val);
        }
    }
}
