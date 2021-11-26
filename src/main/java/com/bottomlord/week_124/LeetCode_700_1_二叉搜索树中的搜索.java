package com.bottomlord.week_124;

import com.bottomlord.TreeNode;

/**
 * @author chen yue
 * @date 2021-11-26 08:54:38
 */
public class LeetCode_700_1_二叉搜索树中的搜索 {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        int cur = root.val;

        if (cur == val) {
            return root;
        }

        if (cur < val) {
            return searchBST(root.right, val);
        } else {
            return searchBST(root.left, val);
        }
    }
}
