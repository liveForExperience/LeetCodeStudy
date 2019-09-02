package com.bottomlord.week_002;

import com.bottomlord.Node;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author LiveForExperience
 * @date 2019/7/16 18:08
 */
public class LeetCode_559_3 {
    public int maxDepth(Node root) {
        int depth = 0;

        if (root == null) {
            return depth;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();
            depth++;
            while (count > 0) {
                count--;
                Node node = queue.poll();
                for (Node child: node.children) {
                    queue.offer(child);
                }
            }
        }

        return depth;
    }
}