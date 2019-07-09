package com.bottomlord.week_1;

import com.bottomlord.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author LiveForExperience
 * @date 2019/7/9 17:56
 */
public class LeetCode_617_1 {
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
