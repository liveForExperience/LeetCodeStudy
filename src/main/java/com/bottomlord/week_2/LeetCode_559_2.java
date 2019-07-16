package com.bottomlord.week_2;

import com.bottomlord.Node;

/**
 * @author LiveForExperience
 * @date 2019/7/16 13:30
 */
public class LeetCode_559_2 {
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }

        int max = 0;
        for (Node node: root.children) {
            int depth = maxDepth(node);
            max = Math.max(max, depth);
        }

        return max + 1;
    }
}
