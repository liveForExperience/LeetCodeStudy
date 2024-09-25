package com.bottomlord.week_014;

import com.bottomlord.TreeNode;

public class LeetCode_889_1_根据前序和后续遍历构造二叉树 {
    private int preIndex, postIndex;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        TreeNode node = new TreeNode(pre[preIndex++]);
        if (node.val != post[postIndex]) {
            node.left = constructFromPrePost(pre, post);
        }

        if (node.val != post[postIndex]) {
            node.right = constructFromPrePost(pre, post);
        }

        postIndex++;
        return node;
    }
}
