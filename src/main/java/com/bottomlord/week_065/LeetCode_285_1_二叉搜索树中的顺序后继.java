package com.bottomlord.week_065;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/9/28 8:25
 */
public class LeetCode_285_1_二叉搜索树中的顺序后继 {
    private TreeNode ans = null;
    private boolean find = false;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        dfs(root, p);
        return ans;
    }

    private void dfs(TreeNode node, TreeNode p) {
        if (node == null) {
            return;
        }

        dfs(node.left, p);

        if (node.val > p.val && !find) {
            ans = node;
            find = true;

            dfs(node.right, p);
        }
    }
}
