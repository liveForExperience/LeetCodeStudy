package com.bottomlord.week_002;

import com.bottomlord.Node;

/**
 * @author LiveForExperience
 * @date 2019/7/16 13:10
 */
public class LeetCode_559_1_N叉树的最大深度 {
    public int maxDepth(Node root) {
        return root == null ? 0 : dfs(root, 0);
    }

    private int dfs(Node node, int count) {
        if (node.children.size() == 0) {
            return ++count;
        }

        int depth = 0;
        for (Node child: node.children) {
            int tmp = dfs(child, count + 1);
            depth = depth >=  tmp ? depth: tmp;
        }

        return depth;
    }
}
