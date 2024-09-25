package com.bottomlord.week_010;

import com.bottomlord.TreeNode;

public class LeetCode_1008_2 {
    private int predex;
    public TreeNode bstFromPreorder(int[] preorder) {
        this.predex = 0;
        return recurse(preorder.length, preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode recurse(int len, int[] preorder, int low, int high) {
        if (predex == len) {
            return null;
        }

        int val = preorder[predex];
        if (val < low || val > high) {
            return null;
        }

        predex++;
        TreeNode root = new TreeNode(val);
        root.left = recurse(len, preorder, low, val);
        root.right = recurse(len, preorder, val, high);
        return root;
    }
}
