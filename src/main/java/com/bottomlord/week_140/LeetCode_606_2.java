package com.bottomlord.week_140;

import com.bottomlord.TreeNode;

/**
 * @author chen yue
 * @date 2022-03-19 14:14:58
 */
public class LeetCode_606_2 {
    public String tree2str(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.substring(1, sb.length() - 1).toString();
    }

    private void dfs(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        sb.append("(");

        sb.append(node.val);

        if (node.left != null) {
            dfs(node.left, sb);
        }

        if (node.right != null) {
            if (node.left == null) {
                sb.append("(").append(")");
            }

            dfs(node.right, sb);
        }

        sb.append(")");
    }
}
