package com.bottomlord.week_038;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/3/26 12:38
 */
public class Interview_0412_1_求和路径 {
    private int ans;
    public int pathSum(TreeNode root, int sum) {
        dfs1(root,sum);
        return ans;
    }

    private void dfs1(TreeNode node, int sum) {
        if (node == null) {
            return;
        }

        dfs2(node, sum, 0);
        dfs1(node.left, sum);
        dfs1(node.right, sum);
    }

    private void dfs2(TreeNode node, int sum, int tmp) {
        if (node == null) {
            return;
        }

        tmp += node.val;
        if (tmp == sum) {
            ans++;
        }

        dfs2(node.left, sum, tmp);
        dfs2(node.right, sum, tmp);
    }
}
