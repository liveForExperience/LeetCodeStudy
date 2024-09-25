package com.bottomlord.week_001;

import com.bottomlord.TreeNode;

/**
 * @author LiveForExperience
 * @date 2019/7/9 17:56
 */
public class LeetCode_617_1_合并二叉树 {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }

        if (t2 == null) {
            return t1;
        }

        TreeNode node = new TreeNode(t1.val + t2.val);
        node.left = mergeTrees(t1.left, t2.left);
        node.right = mergeTrees(t1.right, t2.right);
        return node;
    }
}
