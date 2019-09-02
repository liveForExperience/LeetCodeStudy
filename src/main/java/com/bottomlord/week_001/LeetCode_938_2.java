package com.bottomlord.week_001;

import com.bottomlord.TreeNode;

/**
 * @author LiveForExperience
 * @date 2019/7/8 21:06
 */
public class LeetCode_938_2 {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }

        if (root.val < L) {
            return rangeSumBST(root.right, L, R);
        }

        if (root.val > R) {
            return rangeSumBST(root.left, L, R);
        }

        return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
    }
}
