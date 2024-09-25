package com.bottomlord.week_061;

import com.bottomlord.TreeNode;

import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/9/1 18:32
 */
public class LeetCode_250_2 {
    private int count = 0;
    public int countUnivalSubtrees(TreeNode root) {
        dfs(root, null);
        return count;
    }

    private boolean dfs(TreeNode node, Integer preVal) {
        if (node == null) {
            return true;
        }

        if (!dfs(node.left, node.val) | !dfs(node.right, node.val)) {
            return false;
        }

        count++;

        return Objects.equals(node.val, preVal);
    }
}