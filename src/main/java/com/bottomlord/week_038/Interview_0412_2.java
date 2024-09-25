package com.bottomlord.week_038;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/3/26 14:47
 */
public class Interview_0412_2 {
    public int pathSum(TreeNode root, int sum) {
        return dfs(root, new int[1000], 0, sum);
    }

    private int dfs(TreeNode node, int[] arr, int depth, int sum) {
        if (node == null) {
            return 0;
        }

        int val = node.val, cur = 0;
        arr[depth] = val;

        for (int i = depth - 1; i >= 0; i--) {
            val += arr[i];
            if (val == sum) {
                cur++;
            }
        }

        return cur + dfs(node.left, arr, depth + 1, sum) + dfs(node.right, arr, depth + 1, sum);
    }
}