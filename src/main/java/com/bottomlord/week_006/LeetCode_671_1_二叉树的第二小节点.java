package com.bottomlord.week_006;

import com.bottomlord.TreeNode;

public class LeetCode_671_1_二叉树的第二小节点 {
    public int findSecondMinimumValue(TreeNode root) {
        Integer[] arr = new Integer[]{root.val, null};
        dfs(root, arr);
        if (arr[0].equals(arr[1]) || arr[1] == null) {
            return -1;
        }
        return arr[1];
    }

    private void dfs(TreeNode node, Integer[] arr) {
        if (node == null) {
            return;
        }

        int val = node.val;
        if (val < arr[0]) {
            arr[1] = arr[0];
            arr[0] = val;
        } else if (arr[1] == null || val != arr[0] && val < arr[1]) {
            arr[1] =val;
        }

        dfs(node.left, arr);
        dfs(node.right, arr);
    }
}
