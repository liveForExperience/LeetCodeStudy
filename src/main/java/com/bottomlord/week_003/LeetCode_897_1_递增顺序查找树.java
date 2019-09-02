package com.bottomlord.week_003;

import com.bottomlord.TreeNode;

/**
 * @author LiveForExperience
 * @date 2019/7/27 14:16
 */
public class LeetCode_897_1_递增顺序查找树 {
    public TreeNode increasingBST(TreeNode root) {
        return rescurse(root, null);
    }

    private TreeNode rescurse(TreeNode node, TreeNode preNode) {
        if (node == null) {
            return preNode;
        }

        TreeNode root = rescurse(node.left, node);
        node.left = null;
        node.right = rescurse(node.right, preNode);
        return root;
    }
}
