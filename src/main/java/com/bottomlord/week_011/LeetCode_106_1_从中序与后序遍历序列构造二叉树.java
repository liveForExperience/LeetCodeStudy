package com.bottomlord.week_011;

import com.bottomlord.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_106_1_从中序与后序遍历序列构造二叉树 {
    private int postIndex;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postIndex = postorder.length - 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return dfs(inorder.length - 1, -1, map, postorder);
    }

    private TreeNode dfs(int head, int tail, Map<Integer, Integer> map, int[] postorder) {
        if (head == tail) {
            return null;
        }

        int rootVal = postorder[postIndex];
        TreeNode node = new TreeNode(rootVal);
        int rootIndex = map.get(rootVal);

        postIndex--;
        node.right = dfs(head, rootIndex, map, postorder);
        node.left = dfs(rootIndex - 1, tail, map, postorder);
        return node;
    }
}
