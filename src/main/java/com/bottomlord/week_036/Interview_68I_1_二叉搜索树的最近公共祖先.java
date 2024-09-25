package com.bottomlord.week_036;

import com.bottomlord.TreeNode;

/**
 * @author ThinkPad
 * @date 2020/3/13 8:07
 */
public class Interview_68I_1_二叉搜索树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        return root;
    }
}
