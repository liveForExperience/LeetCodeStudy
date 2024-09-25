package com.bottomlord.contest_20220508;

import com.bottomlord.TreeNode;

/**
 * @author chen yue
 * @date 2022-05-08 10:29:01
 */
public class Contest_2_1_统计值等于子树平均值的节点数 {
    private int ans = 0;
    public int averageOfSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = dfs(node.left), right = dfs(node.right);
        int sum = left[0] + right[0] + node.val, count = left[1] + right[1] + 1,
                avg = sum / count;

        if (node.val == avg) {
            ans++;
        }

        return new int[]{sum, count};
    }
}
