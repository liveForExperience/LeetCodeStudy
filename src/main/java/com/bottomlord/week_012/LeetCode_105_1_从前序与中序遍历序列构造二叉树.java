package com.bottomlord.week_012;

import com.bottomlord.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_105_1_从前序与中序遍历序列构造二叉树 {
    private int preIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return dfs(0, inorder.length, preorder, map);
    }

    private TreeNode dfs(int left, int right, int[] preorder, Map<Integer, Integer> map) {
        if (left == right) {
            return null;
        }

        int rootVal = preorder[preIndex];
        TreeNode root = new TreeNode(rootVal);

        preIndex++;

        int rootIndex = map.get(rootVal);
        root.left = dfs(left, rootIndex, preorder, map);
        root.right = dfs(rootIndex + 1, right, preorder, map);

        return root;
    }
}
