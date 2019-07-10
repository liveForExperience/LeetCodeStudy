package com.bottomlord.week_1;

import com.bottomlord.TreeNode;

/**
 * @author LiveForExperience
 * @date 2019/7/8 18:29
 */
public class LeetCode_938_1_二叉搜索树的范围和 {
    public int rangeSumBST(TreeNode root, int L, int R) {
        return count(root, L, R, 0);
    }

    private int count(TreeNode node, int L, int R, int count) {
        if (node == null) {
            return count;
        }
        int value = node.val;

        if (value >= L && value <= R) {
            count += value;
        }

        count = count(node.left, L, R, count);
        count = count(node.right, L, R, count);

        return count;
    }
}
