package com.bottomlord.week_093;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/4/25 8:27
 */
public class LeetCode_897_1_递增顺序搜索树 {
    public TreeNode increasingBST(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        dfs(root, nodes);
        for (int i = 0; i < nodes.size(); i++) {
            TreeNode curNode = nodes.get(i);
            curNode.left = null;
            if (i != nodes.size() - 1) {
                curNode.right = nodes.get(i + 1);
            } else {
                curNode.right = null;
            }
        }
        return nodes.get(0);
    }

    private void dfs(TreeNode node, List<TreeNode> nodes) {
        if (node == null) {
            return;
        }

        dfs(node.left, nodes);
        nodes.add(node);
        dfs(node.right, nodes);
    }
}
