package com.bottomlord.week_035;

import com.bottomlord.TreeNode;

/**
 * @author ThinkPad
 * @date 2020/3/5 8:29
 */
public class Interview_55I_1_二叉树的深度 {
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
