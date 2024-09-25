package com.bottomlord.week_094;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2021/4/27 8:33
 */
public class LeetCode_938_1_二叉搜索树的范围和 {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        int ans = 0, val = root.val;
        if (val >= low && val <= high) {
            ans += val;
        }

        return ans +
               (val < low ? 0 : rangeSumBST(root.left, low, high)) +
               (val < high ? 0 : rangeSumBST(root.right, low, high));
    }
}
