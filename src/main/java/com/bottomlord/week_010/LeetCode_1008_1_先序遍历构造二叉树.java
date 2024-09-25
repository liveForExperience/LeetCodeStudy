package com.bottomlord.week_010;

import com.bottomlord.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCode_1008_1_先序遍历构造二叉树 {
    private int predex = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        int[] inorder = Arrays.copyOf(preorder, preorder.length);
        Arrays.sort(inorder);

        Map<Integer, Integer> map = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return dfs(preorder, 0, preorder.length, map);
    }

    private TreeNode dfs(int[] preorder, int left, int right, Map<Integer, Integer> map) {
        if (left == right) {
            return null;
        }

        int rootVal = preorder[predex];
        TreeNode root = new TreeNode(rootVal);

        int rootIndex = map.get(rootVal);
        predex++;

        root.left = dfs(preorder, left, rootIndex, map);
        root.right = dfs(preorder, rootIndex + 1, right, map);
        return root;
    }
}
