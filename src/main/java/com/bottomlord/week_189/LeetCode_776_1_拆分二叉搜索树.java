package com.bottomlord.week_189;

import com.bottomlord.TreeNode;

/**
 * @author chen yue
 * @date 2023-02-24 19:44:51
 */
public class LeetCode_776_1_拆分二叉搜索树 {
    public TreeNode[] splitBST(TreeNode root, int target) {
        if (root == null) {
            return new TreeNode[]{null, null};
        }

        TreeNode[] arr = new TreeNode[2];
        int val = root.val;
        if (val <= target) {
            arr[0] = root;
            TreeNode[] rightArr = splitBST(root.right, target);
            root.right = rightArr[0];
            arr[1] = rightArr[1];
        } else {
            arr[1] = root;
            TreeNode[] leftArr = splitBST(root.left, target);
            root.left = leftArr[1];
            arr[0] = leftArr[0];
        }

        return arr;
    }
}
