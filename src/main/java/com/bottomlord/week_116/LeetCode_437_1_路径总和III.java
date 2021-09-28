package com.bottomlord.week_116;

import com.bottomlord.TreeNode;
import org.omg.CORBA.NO_IMPLEMENT;

/**
 * @author chen yue
 * @date 2021-09-28 08:35:00
 */
public class LeetCode_437_1_路径总和III {
    private int count = 0;
    public int pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return count;
    }

    private void dfs(TreeNode node, int sum) {
        if (node == null) {
            return;
        }

        if (node.val == sum) {
            count++;
        }

        doDfs(node.left, node.val, sum);
        doDfs(node.right, node.val, sum);

        dfs(node.left, sum);
        dfs(node.right, sum);
    }

    private void doDfs(TreeNode node, int val, int sum) {
        if (node == null) {
            return;
        }

        val += node.val;

        if (val == sum) {
            count++;
        }

        doDfs(node.left, val, sum);
        doDfs(node.right, val, sum);
    }
}
