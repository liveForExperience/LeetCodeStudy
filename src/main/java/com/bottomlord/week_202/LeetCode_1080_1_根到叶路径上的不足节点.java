package com.bottomlord.week_202;

import com.bottomlord.TreeNode;

/**
 * @author chen yue
 * @date 2023-05-22 09:46:46
 */
public class LeetCode_1080_1_根到叶路径上的不足节点 {
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if (dfs(root, limit, 0)) {
            return null;
        }

        return root;
    }

    private boolean dfs(TreeNode node, int limit, int pre) {
        if (node.left == null && node.right == null) {
            return pre + node.val < limit;
        }

        boolean leftDel = true, rightDel = true;
        if (node.left != null) {
            leftDel = dfs(node.left, limit, pre + node.val);
        }

        if (node.right != null) {
            rightDel = dfs(node.right, limit, pre + node.val);
        }

        if (leftDel) {
            node.left = null;
        }

        if (rightDel) {
            node.right = null;
        }

        return leftDel && rightDel;
    }
}
