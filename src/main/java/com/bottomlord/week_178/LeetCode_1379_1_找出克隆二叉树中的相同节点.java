package com.bottomlord.week_178;

import com.bottomlord.TreeNode;

/**
 * @author chen yue
 * @date 2022-12-07 21:05:23
 */
public class LeetCode_1379_1_找出克隆二叉树中的相同节点 {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || cloned == null) {
            return null;
        }

        if (original == target) {
            return cloned;
        }

        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if (left != null) {
            return left;
        }

        return getTargetCopy(original.right, cloned.right, target);
    }
}
