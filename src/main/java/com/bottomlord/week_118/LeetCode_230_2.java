package com.bottomlord.week_118;

import com.bottomlord.TreeNode;

/**
 * @author chen yue
 * @date 2021-10-18 08:49:48
 */
public class LeetCode_230_2 {
    private int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        return dfs(root, k);
    }

    private Integer dfs(TreeNode node, int k) {
        if (node == null) {
            return null;
        }

        Integer val = dfs(node.left, k);
        if (val != null) {
            return val;
        }

        if (++count == k) {
            return node.val;
        }

        return dfs(node.right, k);
    }
}
