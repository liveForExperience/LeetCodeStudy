package com.bottomlord.week_038;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/3/24 8:41
 */
public class Interview_0405_2 {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }

        return node.val < max && node.val > min &&
                dfs(node.left, min, node.val) &&
                dfs(node.right, node.val, max);
    }
}
