package com.bottomlord.week_005;

import com.bottomlord.TreeNode;

public class LeetCode_606_2 {
    public String tree2str(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        dfs(t, sb);
        return sb.toString();
    }

    private void dfs(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        sb.append(node.val);

        if (node.left == null && node.right == null) {
            return;
        }

        sb.append("(");
        dfs(node.left, sb);
        sb.append(")");

        if (node.right != null) {
            sb.append("(");
            dfs(node.right, sb);
            sb.append(")");
        }
    }
}