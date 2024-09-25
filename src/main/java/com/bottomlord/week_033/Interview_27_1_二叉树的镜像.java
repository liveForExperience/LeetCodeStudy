package com.bottomlord.week_033;

import com.bottomlord.TreeNode;

/**
 * @author ThinkPad
 * @date 2020/2/23 10:30
 */
public class Interview_27_1_二叉树的镜像 {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = root.left, right = root.right;

        root.left = mirrorTree(right);
        root.right = mirrorTree(left);
        return root;
    }
}
