package com.bottomlord.week_215;

import com.bottomlord.TreeNode;

/**
 * @author chen yue
 * @date 2023-08-25 09:22:01
 */
public class LeetCode_1448_1_统计二叉树中好节点的数目 {
    private int cnt;

    public int goodNodes(TreeNode root) {
        this.cnt = 0;
        dfs(root, root.val);
        return cnt;
    }

    private void dfs(TreeNode node, int max) {
        if (node == null) {
            return;
        }

        if (node.val >= max) {
            cnt++;
        }

        max = Math.max(max, node.val);
        dfs(node.left, max);
        dfs(node.right, max);
    }
}
