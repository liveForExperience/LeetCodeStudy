package com.bottomlord.week_064;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/9/22 8:31
 */
public class LeetCode_968_1_监控二叉树 {
    int camera = 0;
    public int minCameraCover(TreeNode root) {
        return (dfs(root) == 0 ? 1 : 0) + camera;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 2;
        }

        int left = dfs(node.left), right = dfs(node.right);

        if (left == 0 || right == 0) {
            camera++;
            return 1;
        }

        return left == 1 || right == 1 ? 2 : 0;
    }
}
