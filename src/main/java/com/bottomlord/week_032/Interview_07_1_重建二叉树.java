package com.bottomlord.week_032;

import com.bottomlord.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ThinkPad
 * @date 2020/2/15 17:03
 */
public class Interview_07_1_重建二叉树 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preLen = preorder.length, inLen = inorder.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inLen; i++) {
            map.put(inorder[i], i);
        }

        return buildTree(0, preLen - 1, 0, inLen - 1, map, preorder);
    }

    private TreeNode buildTree(int preHead, int preTail, int inHead, int inTail, Map<Integer, Integer> map, int[] preorder) {
        if (preHead > preTail || inHead > inTail) {
            return null;
        }

        int rootVal = preorder[preHead], rootIndex = map.get(rootVal);
        TreeNode root = new TreeNode(rootVal);

        root.left = buildTree(preHead + 1, preHead + rootIndex - inHead, inHead, rootIndex - 1, map, preorder);
        root.right = buildTree(preHead + rootIndex - inHead + 1, preTail, rootIndex + 1, inTail, map, preorder);
        return root;
    }
}
