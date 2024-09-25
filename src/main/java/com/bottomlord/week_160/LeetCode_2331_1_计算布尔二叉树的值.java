package com.bottomlord.week_160;

import com.bottomlord.TreeNode;

/**
 * @author chen yue
 * @date 2022-08-07 19:15:57
 */
public class LeetCode_2331_1_计算布尔二叉树的值 {
    public boolean evaluateTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        int val = root.val;
        if (val < 2) {
            return val == 1;
        }

        boolean left = evaluateTree(root.left), right = evaluateTree(root.right);
        if (val == 2) {
            return left || right;
        } else {
            return left && right;
        }
    }
}
